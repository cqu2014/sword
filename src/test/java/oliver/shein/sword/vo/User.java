package oliver.shein.sword.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/10 8:39
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private int age;

    @Override
   public String toString(){
    return "User [name="+name+", age="+age+"]";
   }
}
