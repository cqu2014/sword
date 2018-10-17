package oliver.shein.sword.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Person {
    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL_NAME",ordinal = 2)
    private String fullName;

    @JSONField(name="FIRST NAME", ordinal = 1,deserialize = false)
    private String firstName;

    @JSONField(name = "DATE OF BIRTH",ordinal = 3,format = "yyyy-MM-dd")
    private Date dateOfBirth;
}
