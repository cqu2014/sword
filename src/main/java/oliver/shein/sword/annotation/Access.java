package oliver.shein.sword.annotation;

import java.lang.annotation.*;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/18 15:54
 */
@Target(ElementType.METHOD) //能够标注的位置
@Retention(RetentionPolicy.RUNTIME) //注解的注解保留时期
@Documented //在生成接口文档时是否生成注解文档
public @interface Access {
    String[] value() default {};
    String[] authorities() default {};
    String[] roles() default {};
}
