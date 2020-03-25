package oliver.shein.sword.dto;

import lombok.Builder;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/3/25
 * @since
 */
@Builder
public class Student {
    private String name;
    private String sex;
    private Long age;

    /**
     * 返回属性值列表
     *
     * @return
     */
    public List<String> getAttribute(){
        List<String> attributes = new LinkedList<>();
        attributes.add(this.name);
        attributes.add(this.sex);
        attributes.add(String.valueOf(this.age));
        return attributes;
    }
}
