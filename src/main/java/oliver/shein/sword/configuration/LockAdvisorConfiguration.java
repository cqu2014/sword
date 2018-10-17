package oliver.shein.sword.configuration;

import oliver.shein.sword.core.LockAdvisor;
import oliver.shein.sword.interceptor.RedissonLockInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:10
 */
@Configuration
public class LockAdvisorConfiguration {
    @Resource
    RedissonLockInterceptor redissonLockInterceptor;

    @Bean
    public LockAdvisor lockAdvisorConfig(){
        LockAdvisor lockAdvisor = new LockAdvisor();
        lockAdvisor.setAdvice(redissonLockInterceptor);

        return lockAdvisor;
    }
}
