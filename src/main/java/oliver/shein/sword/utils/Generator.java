package oliver.shein.sword.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author Oliver Wang
 * @Description 工具类
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:15
 */
@Slf4j
public class Generator {
    /**
     * 生成taskid long
     *
     * @return
     */
    public static  long generateTaskId(){
        try {
            Long now = System.currentTimeMillis();
            Random random = new Random();
            int randNum = random.nextInt(9999)+10000;
            String randStr = now +String.valueOf(randNum);
            return Long.parseLong(randStr);
        }catch (Exception e){
            log.error("生成随机的taskId失败");
            return System.currentTimeMillis();
        }
    }
}
