package oliver.shein.sword.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
@ApiModel(value = "用户信息",description = "用户信息")
public class UserInfo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("用户姓名")
    @Size(max = 20)
    private String name;

    @ApiModelProperty("年龄")
    @Max(150)
    @Min(10)
    private int age;

    @ApiModelProperty("家庭住址")
    @NotNull
    private String address;

    @ApiModelProperty("电子邮件")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;
}
