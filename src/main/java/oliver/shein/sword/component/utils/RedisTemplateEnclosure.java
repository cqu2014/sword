package oliver.shein.sword.component.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description {RedisTemplate封装}
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/22 14:33
 */
@Component
public class RedisTemplateEnclosure {
    @Resource
    RedisTemplate redisTemplate;

    /**
     * 封装jedis方法
     *
     * @param key
     * @return
     */
    public String get(final String key){
        Object object = redisTemplate.opsForValue().get(key);
        return object == null ?null:object.toString();
    }

    /**
     * 设置新值返回老值
     *
     * @param key
     * @param value
     * @return
     */
    public String getSet(final String key,final String value){
        Object object = redisTemplate.opsForValue().getAndSet(key,value);
        return object == null ? null:object.toString();
    }
    /**
     * 成功-true 失败-false
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(final String key,final String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    /**
     * 删除功能
     *
     * @param key
     */
    public void delete(final String key){
        redisTemplate.delete(key);
    }
}
