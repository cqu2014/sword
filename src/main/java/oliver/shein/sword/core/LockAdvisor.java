package oliver.shein.sword.core;

import oliver.shein.sword.annotation.Lock;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @Author Oliver Wang
 * @Description lock锁的监听者
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:09
 */
public class LockAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Lock lockAnno = method.getAnnotation(Lock.class);
        return lockAnno == null ? false : true;
    }
}
