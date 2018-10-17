package oliver.shein.sword.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.IHanoiService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:16
 */
@Component
@Slf4j
@NoArgsConstructor
public class HanoiThread  implements Runnable{
    /**
     * spring 处于安全考虑Runnable实现类不能使用
     * @Resource注解注入依赖
     */
    private static IHanoiService iHanoiService;

    private int threadNo;

    public HanoiThread(int threadNo) {
        this.threadNo = threadNo;
    }

    @Resource
    public void setHanoiService(IHanoiService hanoiService) {
        HanoiThread.iHanoiService = hanoiService;
    }

    @Override
    public void run() {
        try {
            iHanoiService.hanoi("oliver",this.threadNo);
        }catch (Exception e){
            log.error("线程{}启动失败{}",e);
        }
    }
}
