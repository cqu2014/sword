package oliver.shein.sword.service;

import oliver.shein.sword.annotation.RedisLock;

/**
 * @Author Oliver Wang
 * @Description 秒杀代码
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/19 13:47
 */
public interface IMsService {
    /**
     * 抢购代码
     *
     * @param key
     * @return
     */
    boolean seckill(String key,long threadNo);

    /**
     * 抢购代码
     *
     * @param threadNo
     * @return
     */
    void sckill(long threadNo);
}
