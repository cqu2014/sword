package oliver.shein.sword.polym;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 16:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SonPloym extends FatherPloym {
    private String girlFriend;

    public SonPloym(){}

    public SonPloym(String name,int sex,String girlFriend) {
        this.name = name;
        this.sex = sex;
        this.girlFriend = girlFriend;
    }
}
