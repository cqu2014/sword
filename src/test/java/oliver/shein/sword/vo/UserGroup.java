package oliver.shein.sword.vo;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/10 8:48
 */
@Data
public class UserGroup {
    private String name;
    private List<User> users = new LinkedList<>();

    @Override
    public String toString(){
        return "UserGroup [name=" + name + ", users=" + users + "]";
    }
}
