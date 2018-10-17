package oliver.shein.sword;

import com.alibaba.fastjson.JSON;
import oliver.shein.sword.vo.RiskEmailTemplate;
import oliver.shein.sword.vo.User;
import oliver.shein.sword.vo.UserGroup;
import org.junit.Test;

import java.util.List;

/**
 * @Author Oliver Wang
 *
 * @Description  Fastjson 对象或数组转JSON &&
 *          Fastjson Obejct/Map/JSON/String 互转
 *
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/9 19:11
 */
public class JsonConvert {
    private String jsonStr = "[{\"language\":\"en\",\"site_uid\":[\"www\",\"m\",\"iosshother\",\"andshother\",\"android\",\"ios\"],\"content\":{\"email_type\":\"html\",\"email_title\":\"123\",\"email_template_content\":\"123123123\"}}]";

    @Test
    public void convert(){
        /**
         * 将json串转化为javaBean对象
         * 并且java对象中包含JavaBean集合
         * 好强大
         */
        List<RiskEmailTemplate> riskEmailTemplateList = JSON.parseArray(jsonStr, RiskEmailTemplate.class);

        System.out.println(riskEmailTemplateList.get(0).getSiteUid().get(2));
    }

    @Test
    public void arrayUtils(){
        User guestUser = new User();
        guestUser.setName("guest")
                .setAge(28);
        User rootUser = new User();
        rootUser.setName("root")
                .setAge(35);

        UserGroup group = new UserGroup();
        group.setName("admin");
        group.getUsers().add(guestUser);
        group.getUsers().add(rootUser);

        String formatString = JSON.toJSONString(group,true);
        System.out.println("formatString: "+formatString);
        System.out.println("**********************************************************");

        String jsonString = JSON.toJSONString(group);
        System.out.println("jsonString: "+jsonString);
        System.out.println("**********************************************************");

        UserGroup newGroup = JSON.parseObject(jsonString,UserGroup.class);
        System.out.println("newGroup: "+newGroup);
        System.out.println("**********************************************************");

        // 构建用户对象数组
        User[] users = new User[2];
        users[0] = guestUser;
        users[1] = rootUser;
        // 用户对象数组转JSON串
        String jsonString2 = JSON.toJSONString(users);
        /**
         * jsonString2:[{"age":28,"name":"guest"},{"age":35,"name":"root"}]
         * 在json反序列化中，数组是使用List标示的
         */
        System.out.println("jsonString2:" + jsonString2);
        System.out.println("**********************************************************");

        List<User> userList = JSON.parseArray(jsonString2,User.class);
        System.out.println("userList: "+userList);
    }

    @Test
    public void toJsonTest(){
        User guestUser = new User();
        guestUser.setName("guest")
                .setAge(28);

        Object jsonObject = JSON.toJSON(guestUser);

        System.out.println(jsonObject);
    }
}
