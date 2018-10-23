package oliver.shein.sword.annotation;

import java.lang.annotation.*;

/**
 * @Author Oliver Wang
 * @Description 基于jedis的分布式锁
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/22 13:55
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    String prefix() default "";
    String key() default "";
    /**
     * 拥有锁的最长时间-防无限执行
     *
     * @return
     */
    int expireMsecs() default 30*1000;

    /**
     * 等待锁的最长时长-防饥饿
     *
     * @return
     */
    int timeoutMsecs() default 10*1000;

    /**
     * 重试获取锁的阻塞时间
     *
     * @return
     */
    int retries() default 10;
}
