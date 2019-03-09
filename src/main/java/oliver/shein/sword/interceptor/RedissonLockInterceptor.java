package oliver.shein.sword.interceptor;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.Lock;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 9:43
 */
@Component
@Slf4j
public class RedissonLockInterceptor implements MethodInterceptor {
    /**
     * 获取方法的参数名称 paramName
     */
    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    /**
     * /阻塞时间
     */
    private int timeWait;
    /**
     * 系统名称作为分布式锁的前缀
     */
    public static final String SYSTEM_NAME = "OLIVER_SWORD|";

    @Resource
    RedissonClient redissonClient;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //获取拦截的注解
        Lock lock = invocation.getMethod().getAnnotation(Lock.class);
        String prefix = lock.prefix();
        String value = lock.value();
        boolean block = lock.block();
        timeWait = lock.timeWait();

        String tail = "lock";

        //value非空情况下
        if (!StringUtils.isEmpty(value)) {
            //表达式计算器
            ExpressionParser expressionParser = new SpelExpressionParser();
            Expression expression = expressionParser.parseExpression(value);
            EvaluationContext context = new StandardEvaluationContext();
            String[] argNames = parameterNameDiscoverer.getParameterNames(invocation.getMethod());
            Object[] args = invocation.getArguments();
            for (int i = 0; i < args.length; i++) {
                context.setVariable(argNames[i], args[i]);
            }
            tail = expression.getValue(context).toString(); //value值
        }

        String lockKey = this.SYSTEM_NAME + prefix + "|" + tail;
        RLock rLock = redissonClient.getLock(lockKey);
        /**
         * 根据block获取不同的锁
         */
        if (block) {
            rLock.lock(timeWait, TimeUnit.SECONDS);
            log.info("添加全局锁阻塞成功");
        } else {
            if (!rLock.tryLock()) {
                log.info("非阻塞锁{}被锁住，获取失败", lockKey);
                throw new Exception("获取非阻塞锁失败");
            }
            log.info("添加非阻塞锁成功");
        }

        Object ret;
        try {
            //执行注解修饰的方法
            ret = invocation.proceed();
        } finally {
            if (!StringUtils.isEmpty(lockKey)) {
                rLock.unlock();
            }
        }
        return ret;
    }
}
