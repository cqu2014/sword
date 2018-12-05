package oliver.shein.sword;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/3 10:28
 */
@Slf4j
public class TryCatchTests {
    public static void main(String[] args) {
        try {
            int i = 100/0;
            System.out.println(i);
        }catch (ArithmeticException exc){
            log.error("获取信息报错{}",exc);
            System.out.println();
            System.out.println("+++++++++++++++++++++++");
            log.error("获取信息报错{}",exc.toString());
            System.out.println(exc);
            System.out.println("---------------");
        }catch (Exception ex){
            System.out.println("下面捕获了上面的异常"+ex.getMessage());
        }

        System.out.println("最后的语句");
    }
}
