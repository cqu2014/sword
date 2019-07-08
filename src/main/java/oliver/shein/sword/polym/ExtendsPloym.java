package oliver.shein.sword.polym;

import oliver.shein.sword.utils.JacksonUtil;

import java.util.Optional;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 16:09
 */
public class ExtendsPloym {
    private static final String JSON_STRING = "{\"name\":\"Oliver Wang\",\"sex\":0,\"girlFriend\":\"someone\"}";

    public static void main(String[] args) {
        Optional<SonPloym> optional = JacksonUtil.readValue(JSON_STRING, SonPloym.class);
        optional.ifPresent(System.out::println);

        System.out.println("----------------------------");

        SonPloym sonPloym = optional.orElse(new SonPloym());
        System.out.println(sonPloym);

        /**
         * 父对象引用子类实例时
         * 如果子类中重写了父类中的一个方法，那么在调用这个方法的时候，将会调用子类中的这个方法
         */
        optional.map(x-> (FatherPloym)x).ifPresent(System.out::println);

        System.out.println("----------------------------");

        Optional<FatherPloym> optionalFatherPloym = JacksonUtil.readValue(JSON_STRING,FatherPloym.class);

        /**
         * 只有父类对象本身就是用子类new出来的时候, 才可以在将来被强制转换为子类对象
         * 非子类对象new出来的异常
         */
        SonPloym sonPloy1 = (SonPloym) optionalFatherPloym.orElse(null);
        System.out.println(sonPloy1);
    }
}
