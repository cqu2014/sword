package oliver.shein.sword.lock;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.core.constant.Constants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/19 10:32
 */
@Component
@Slf4j
@NoArgsConstructor
public class RedisLock {

    private RedisTemplate redisTemplate;

    /**
     * 重试时间
     * 单位 毫秒
     */
    private static final int DEFAULT_ACQUIRE_RESOLUTION_MILLIS = 100;

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     * 单位 毫秒
     */
    private int expireMsecs = 60 * 1000;
    /**
     * 锁等待时间，防止线程饥饿
     * 单位 毫秒
     */
    private int timeoutMsecs = 10 * 1000;
    /**
     * 是否锁住标志
     */
    private volatile boolean locked = false;
    /**
     * 锁的主键key
     */
    private String lockKey;

    /**
     * 构造器
     * @param lockKey
     */
    public RedisLock(RedisTemplate redisTemplate,String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey+ Constants.LOCK_SUFFIX;
    }

    /**
     * 构造器
     * @param lockKey
     * @param timeout
     */
    public RedisLock(RedisTemplate redisTemplate,String lockKey,int timeout){
        this(redisTemplate,lockKey);
        this.timeoutMsecs = timeout;
    }

    /**
     * 构造器
     * @param lockKey
     * @param timeoutMsecs
     * @param expireMsecs
     */
    public RedisLock(RedisTemplate redisTemplate,String lockKey, int timeoutMsecs,int expireMsecs) {
        this(redisTemplate,lockKey,timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     * 封装jedis方法
     *
     * @param key
     * @return
     */
    private String get(final String key){
        Object object = redisTemplate.opsForValue().get(key);
        return object == null ?null:object.toString();
    }

    /**
     * 成功-true 失败-false
     *
     * @param key
     * @param value
     * @return
     */
    private boolean setNx(final String key,final String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    /**
     * getAndSet
     *
     * @param key
     * @param value
     * @return
     */
    private String getSet(final String key,final String value){
        Object object = redisTemplate.opsForValue().getAndSet(key,value);
        return object == null ? null:object.toString();
    }

    /**
     * 获取锁
     *
     * @return 获取锁成功返回true，超时返回false
     * @throws InterruptedException
     */
    public synchronized boolean lock() throws InterruptedException{
        int timeout = this.timeoutMsecs;
        while (timeout >= 0){
            long expires = System.currentTimeMillis() + expireMsecs +1;
            String expireStr = String.valueOf(expires); //锁到期时间
            //获取锁成功
            if (this.setNx(lockKey,expireStr)){
                locked = true;
                return true;
            }

            //获取redis里锁的有效时间
            String currentValue = this.get(lockKey);

            //判断锁是否已经过期，过期则重新设置并获取
            if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis()){
                //设置锁并返回旧值
                String oldValue = this.getSet(lockKey,expireStr);
                //比较锁的时间，如果不一致则可能是其他锁已经修改了值并获取
                if (oldValue !=null && oldValue.equals(currentValue)){
                        locked = true;
                        return true;
                }

                timeout -= this.DEFAULT_ACQUIRE_RESOLUTION_MILLIS;

                /**
                 * 随机阻塞一段时间
                 */
                Random random = new Random(25);
                Thread.sleep(random.nextInt(10000));
            }

        }
        return false;
    }

    /**
     * 释放获取到的锁
     */
    public synchronized  void unlock(){
        if (locked){
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }
}
