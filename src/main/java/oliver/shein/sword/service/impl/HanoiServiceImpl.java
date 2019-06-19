package oliver.shein.sword.service.impl;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.Lock;
import oliver.shein.sword.service.IHanoiService;
import oliver.shein.sword.utils.Generator;
import org.springframework.stereotype.Service;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:14
 */
@Slf4j
@Service
public class HanoiServiceImpl implements IHanoiService {
    /**
     * 移动汉诺塔
     *
     * @param n
     * @param start
     * @param temp
     * @param target
     */
    @Override
    @Lock(prefix = "hanoi",value = "#n",block = false)
    public void move(int n, char start, char temp, char target){
        if(n==0) {
            return;
        }
        if(n==1) {
            System.out.println(start+"----->"+target);
        } else{
            move(n-1,start,target,temp);
            System.out.println(start+"----->"+target);
            move(n-1,temp,start,target);
        }
    }

    @Override
    @Lock(prefix = "hanoi",value = "#lock",block = true)
    public void hanoi(String lock,int no){
        long methodNo = Generator.generateTaskId();
        log.info("{}开始操作进入锁:{},编号={}",lock,methodNo,no);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("++++++++++++++++++++++++++++++++"+methodNo+"线程释放锁完成:"+lock+",编号="+no);
    }
}
