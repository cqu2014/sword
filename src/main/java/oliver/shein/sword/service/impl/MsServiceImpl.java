package oliver.shein.sword.service.impl;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.lock.RedisLock;
import oliver.shein.sword.service.IMsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/19 13:49
 */
@Component
@Slf4j
public class MsServiceImpl implements IMsService {
    public static final String NUM_PRODUCT_KEY = "pronum";

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public boolean seckill(String key,long threadNo) {
        RedisLock lock = new RedisLock(redisTemplate,key);
        try {
            if (lock.lock()){
                String pronum = (String) redisTemplate.opsForValue().get(NUM_PRODUCT_KEY);
                if (Integer.parseInt(pronum) - 1 >=0){
                    redisTemplate.opsForValue().set(NUM_PRODUCT_KEY,String.valueOf(Integer.parseInt(pronum) - 1));
                    System.out.println("库存数量:"+pronum+" 成功!!!"+threadNo);
                }else {
                    System.out.println("手慢拍大腿");
                }
                return true;
            }
        }catch (InterruptedException e){
            log.error("获取redis锁key={}异常{}",key,e);
        }finally {
            /**
             * 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，
             * 再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
             * 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了
             *
             * 后续做
             */
            lock.unlock();
        }

        return false;
    }
}
