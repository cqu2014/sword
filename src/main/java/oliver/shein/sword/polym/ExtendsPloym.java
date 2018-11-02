package oliver.shein.sword.polym;

import com.alibaba.fastjson.JSON;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 16:09
 */
public class ExtendsPloym {
    public static final String JSON_STRING = "{\"name\":\"Oliver Wang\",\"sex\":0,\"girlFriend\":\"UNKNOWN\"}";

    public static void main(String[] args) {
        FatherPolym fatherPolym = JSON.parseObject(JSON_STRING,FatherPolym.class);

        System.out.println(fatherPolym);

        SonPloym sonPloym = (SonPloym) fatherPolym;
        System.out.println(sonPloym);
    }
}
