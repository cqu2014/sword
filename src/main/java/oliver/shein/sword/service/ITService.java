package oliver.shein.sword.service;

import java.util.List;

/**
 * @Author Oliver Wang
 * @Description 泛型方法的使用
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/18 15:00
 */
public interface ITService {
    /**
     * 泛型方法转化列表
     *
     * @param jsonData
     * @param value
     * @param <T>
     * @return
     */
    <T> T toBean(String jsonData,Class<T> value);

    /**
     * 泛型转化为bean
     *
     * @param jsonData
     * @param valueType
     * @param <T>
     * @return
     */
    <T> List<T> toList(String jsonData,Class<T> valueType);
}
