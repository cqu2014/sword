package oliver.shein.sword.annotation;

import java.lang.annotation.*;

/**
 * @Author Oliver Wang
 * @Description 分布式锁
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 9:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    /**
     * 锁前缀
     * @return
     */
    String prefix() default "sword";

    /**
     * 锁具体的值
     * @return
     */
    String value();

    /**
     * true 全局锁阻塞锁
     * false 非阻塞锁
     *
     * @return
     */
    boolean block();

    /**
     * 阻塞所最长阻塞时间
     * @return
     */
    int timeWait() default 200;
}
