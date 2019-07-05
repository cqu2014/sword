package oliver.shein.sword.polym;

import com.alibaba.fastjson.JSON;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 16:09
 */
public class ExtendsPloym {
    private static final String JSON_STRING = "{\"name\":\"Oliver Wang\",\"sex\":0,\"girlFriend\":\"UNKNOWN\"}";

    public static void main(String[] args) {
        SonPloym sonPloym = JSON.parseObject(JSON_STRING, SonPloym.class);
        System.out.println(sonPloym);

        //如果子类中重写了父类中的一个方法，那么在调用这个方法的时候，将会调用子类中的这个方法
        System.out.println(((FatherPloym) sonPloym).toString());

        System.out.println("----------------------------");

        FatherPloym fatherPloym = JSON.parseObject(JSON_STRING,FatherPloym.class);
        //只有父类对象本身就是用子类new出来的时候, 才可以在将来被强制转换为子类对象
        /**
         * 非子类对象new出来的异常
         */
        SonPloym sonPloy1 = (SonPloym) fatherPloym;
        System.out.println(sonPloy1);
    }
}
