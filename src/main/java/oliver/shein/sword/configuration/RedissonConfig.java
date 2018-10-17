package oliver.shein.sword.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 9:48
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Value("${spring.redis.password}")
    private String passwored;

    @Bean("redissonClient")
    public RedissonClient getRedissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+ redisHost + ":" + redisPort)
                .setPassword(StringUtils.isEmpty(passwored)?null:passwored);

        return Redisson.create(config);
    }

}
