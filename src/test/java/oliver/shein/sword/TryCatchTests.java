package oliver.shein.sword;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/3 10:28
 */
public class TryCatchTests {
    public static void main(String[] args) {
        try {
            int i = 100/0;
            System.out.println(i);
        }catch (ArithmeticException exc){
            System.out.println("除数不能为0");
            throw exc;
        }catch (Exception ex){
            System.out.println("下面捕获了上面的异常"+ex.getMessage());
        }
        System.out.println("最后的语句");
    }
}
