package oliver.shein.sword.polym;

import lombok.Data;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 16:07
 */
@Data
public class SonPloym extends FatherPolym{
    private String girlFriend;

    @Override
    public  String toString(){
        return super.toString()+",girlFriend="+girlFriend;
    }
}
