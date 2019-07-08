package oliver.shein.sword.test;

import com.fasterxml.jackson.core.type.TypeReference;
import oliver.shein.sword.polym.SonPloym;
import oliver.shein.sword.utils.JacksonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/7/8
 * @since
 */
public class JacksonUtilTest {
    public static final String ARRAY_JSON_STRING = "[{\"name\":\"Oliver Wang\",\"sex\":10,\"girlFriend\":\"someone\"},{\"name\":\"er gou\",\"sex\":20,\"girlFriend\":\"beauty\"},{\"name\":\"Jinfu\",\"sex\":30,\"girlFriend\":\"handsome\"}]";
    public static final String MAP_JSON_STRING = "{\"name\":\"Oliver Wang\",\"sex\":10,\"girlFriend\":\"someone\"}";
    public static final String MAP_MULTI_JSON_STRING = "{\"11\":{\"name\":\"11\",\"sex\":10,\"girlFriend\":\"222\"},\"22\":{\"name\":\"22\",\"sex\":10,\"girlFriend\":\"222\"},\"33\":{\"name\":\"33\",\"sex\":10,\"girlFriend\":\"222\"}}";
    @Test
    public void stringToArray(){
        List<SonPloym> sonPloymList = JacksonUtil.readToList(ARRAY_JSON_STRING,SonPloym.class);
        System.out.println(sonPloymList);
    }

    @Test
    public void stringToArray1(){
        List<SonPloym> sonPloymList = JacksonUtil.readToList(ARRAY_JSON_STRING, new TypeReference<List<SonPloym>>() {
        });

        System.out.println(sonPloymList.get(0));
    }

    @Test
    public void stringToMap(){
        Map<String,SonPloym> map = JacksonUtil.readToMap(MAP_MULTI_JSON_STRING,SonPloym.class);
        System.out.println(map);
    }


    @Test
    public void productData(){
        Map<String,SonPloym> sonPloymMap = new HashMap<>();
        sonPloymMap.put("11",new SonPloym("11",10,"222"));
        sonPloymMap.put("22",new SonPloym("22",10,"222"));
        sonPloymMap.put("33",new SonPloym("33",10,"222"));

        System.out.println(JacksonUtil.toJson(sonPloymMap));
    }
}
