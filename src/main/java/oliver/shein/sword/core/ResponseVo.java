package oliver.shein.sword.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/20
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseVo<T> implements Serializable {
    private String code;
    private String msg;
    private T info;

    public static <T> ResponseVo<T> successWithInfo(T info){
        return new ResponseVo<>("200","SUCCESS",info);
    }

    public static ResponseVo successWithEmpty(){
        return successWithInfo(null);
    }
}
