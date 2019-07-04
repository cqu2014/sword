package oliver.shein.sword.aspect;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.RedisLock;
import oliver.shein.sword.component.utils.RedisTemplateEnclosure;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/22 14:04
 */
@Aspect
@Component
@Slf4j
@NoArgsConstructor
public class RedisLockAspect {
    @Autowired
    RedisTemplateEnclosure enclosure;

    @Pointcut(value = "@annotation(oliver.shein.sword.annotation.RedisLock)")
    public void redisLockAnnotation(){}

    @Around("redisLockAnnotation()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        if (!(signature instanceof  MethodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = pjp.getTarget();
        Method method = target.getClass().getDeclaredMethod(methodSignature.getName(),methodSignature.getParameterTypes());
        String methodName = methodSignature.getName();
        log.info("方法{}进入争取分布式锁的进程",methodName);

        RedisLock lock = method.getAnnotation(RedisLock.class);
        if (lock == null){
            log.info("{}方法未被注解[RedisLock]修饰",methodName);
            return pjp.proceed();
        }
        /**
         * 争取异步锁逻辑
         */
        int timeout = lock.timeoutMsecs();
        Object result;
        while (timeout > 0){
            //本次获取锁到期时间
            long expires = System.currentTimeMillis()+ lock.expireMsecs()+1;
            String expireStr = String.valueOf(expires);
            String lockKey = lock.prefix()+"-"+lock.key();

            //redis不存在锁-获取锁成
            if (enclosure.setNx(lockKey,expireStr)){
                result = pjp.proceed();
                unlock(lockKey,expires);
                return result;
            }
            //当redis已存在锁时
            String currentValue = enclosure.get(lockKey);
            //如果锁过期则可以重新获取
            if (!StringUtils.isEmpty(currentValue)
                    && Long.parseLong(currentValue) < System.currentTimeMillis()){
                //将锁设置新值并返回旧值
                String oldValue  = enclosure.getSet(lockKey,expireStr);
                /**
                 * 乐观锁
                 * 如果oldValue不等于初始redis中的value
                 * 可能是其他线程已经修改了值并获取,设置的新值差别很小可以忽略
                 */
                if (currentValue.equals(oldValue)){
                    result = pjp.proceed();
                    unlock(lockKey,expires);
                    return result;
                }
            }
            //阻塞随机时间等待重试试
            Random random = new Random(25);
            //单位毫秒
            int nextValue = random.nextInt(lock.retries());
            timeout -=nextValue;

            Thread.sleep(nextValue);
        }
        log.info("获取锁超时{}，不具有执行权",lock.timeoutMsecs());
        return null;
    }

    /**
     * 解锁
     * @param lockKey
     * @param expires
     */
    private void unlock(String lockKey,long expires){
        if (expires > System.currentTimeMillis()){
            enclosure.delete(lockKey);
        }
    }
}
