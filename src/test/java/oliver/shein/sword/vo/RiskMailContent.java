package oliver.shein.sword.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/9 19:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RiskMailContent {
    /*主题*/
    private String emailTitle;

    /*类型*/
    private String emailType;

    /*复制邮件模板*/
    private String duplicationEmailTemplate;

    /*邮件模板内容*/
    private String emailTemplateContent;

}
