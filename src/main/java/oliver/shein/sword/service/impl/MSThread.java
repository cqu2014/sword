package oliver.shein.sword.service.impl;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.IMsService;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/19 15:06
 */
@Slf4j
public class MSThread implements Runnable{
    private IMsService iMsService;
    private long threadNo;
    private String lockey;

    public MSThread(IMsService iMsService,long threadNo,String lockey) {
        this.iMsService = iMsService;
        this.threadNo = threadNo;
        this.lockey = lockey;
    }

    @Override
    public void run() {
        //log.info("线程{}开始秒杀",threadNo);
        //iMsService.seckill(lockey,threadNo);
        iMsService.sckill(threadNo);
        //log.info("线程{}结束",threadNo);
    }
}
