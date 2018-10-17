package oliver.shein.sword.controller;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.impl.HanoiThread;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/16 14:28
 */
@RestController
@RequestMapping("/lock")
@Slf4j
public class LockController {
    @GetMapping(value = "/hanoi")
    public Object hanoi(){
        int n = 50;
        while(n-->0){
            new Thread(new HanoiThread(n)).start();
            log.info("第"+n+"个线程被启动");
        }
        return "线程跑汉诺塔完成！";
    }

    @GetMapping(value = "/sleep")
    public Object getlock(){
        int n = 50;
        while (n-->0){
            log.info("第"+n+"个线程启动");
            new Thread(new HanoiThread(n)).start();
        }
        return "Over";
    }

}
