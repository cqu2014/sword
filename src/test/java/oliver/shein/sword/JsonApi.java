package oliver.shein.sword;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import lombok.Data;
import oliver.shein.sword.vo.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
public class JsonApi {
    private  List<Person> personList = new LinkedList<>();

    @Before
    public void setUp(){
        personList.add(new Person(15, "John Doe","John",new Date()));
        personList.add(new Person(20, "Janette Doe","Janette",new Date()));
    }

    @Test
    public void jsonStringTest() {
        String jsonOutput = JSON.toJSONString(personList);

        System.out.println(jsonOutput);

        List<Person>  descList = JSON.parseArray(jsonOutput,Person.class);

        System.out.println(descList);

        System.out.println(JSON.toJSONString(descList));

        System.out.println("*******************************************************************");

        String beanToArrayStr = JSON.toJSONString(descList, SerializerFeature.BeanToArray);

        System.out.println(beanToArrayStr);
    }


    @Test
    public void jsonObject(){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL_NAME", "John Doe " + i);
            jsonObject.put("FIRST NAME","John");
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }

        String jsonOut = JSON.toJSONString(jsonArray);
        System.out.println(jsonOut);

        List<Person> personList = JSON.parseArray(jsonOut,Person.class);
        System.out.println(personList);
    }

    @Test
    public void jsonArray(){
        JSONArray jsonArray = new JSONArray();
        Person person1 = new Person(12,"john deo","john",new Date());
        Person person2 = new Person(16,"john deo","jone",new Date());
        jsonArray.add(person1);
        jsonArray.add(person2);

        String jsonOut = JSON.toJSONString(jsonArray);
        System.out.println(jsonOut);

        List<Person> descFromJsonArray = JSON.parseArray(jsonOut,Person.class);

        System.out.println(descFromJsonArray);
    }

    @Test
    public void contextValueFilter(){
        //对每个序列化的值进行过滤筛选
        ContextValueFilter valueFilter = (BeanContext context, Object object, String name, Object value)->{
            if (name.equals("DATE OF BIRTH")){
                return "NOT TO DISCLOSE";
            }

            if (value.equals("John Doe")){
                return ((String)value).toUpperCase();
            }else {
                //return null;
                return value;
            }
        };

        String jsonout = JSON.toJSONString(personList,valueFilter);

        System.out.println(jsonout);
    }


    @Test
    public void givenSerializeConfig_whenJavaObject_thanJsonCorrect(){
        NameFilter nameFilter = (Object object,String name,Object value)->
            name.toLowerCase().replace(" ","_");

        SerializeConfig.getGlobalInstance().addFilter(Person.class,nameFilter);
        String jsonOutput = JSON.toJSONStringWithDateFormat(personList,"yyyy-MM-dd");

        System.out.println(jsonOutput);
    }
}
