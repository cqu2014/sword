package oliver.shein.sword.aspect;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 14:18
 */
@Component
@Aspect
@Slf4j
public class MyLogAspect {
    @Pointcut("@annotation(oliver.shein.sword.annotation.MyLog)")
    public void annotationPointCut(){}

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法名称
        String methodName = signature.getName();
        //获取方法参数
        Parameter[] parameters = method.getParameters();

        MyLog mylog = method.getAnnotation(MyLog.class);
        log.info("方法{}被注解修饰，value={},参数={}",methodName,mylog.value(),
                Arrays.toString(parameters));
    }
}

