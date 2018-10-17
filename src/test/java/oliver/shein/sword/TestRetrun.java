package oliver.shein.sword;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/11 16:28
 */
public class TestRetrun {

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            fun(i);
        }
    }

    public static void fun(int x){
        if (x % 2 == 0){
            return;
        }
        System.out.println("I am fun"+x);
    }


    @Test
    public void mapKeySet(){
        Map<Long,String> map = new HashMap<>();
        map.put(1L,"1l");
        map.put(2L,"1l");
        map.put(3L,"1l");
        map.put(4L,"1l");
        map.put(5L,"1l");

        System.out.println(JSON.toJSONString(map.keySet()));

        List<Long> list = Arrays.asList(1l,2l,3l,4l,5l);

        System.out.println(JSON.toJSONString(list));

    }
}
