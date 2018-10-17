package oliver.shein.sword.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RiskMailContent {
    /*主题*/
    @JsonProperty(value = "email_title")
    private String emailTitle;

    /*类型*/
    @JsonProperty(value = "email_type")
    private String emailType;

    /*复制邮件模板*/
    @JsonProperty(value = "duplication_email_template")
    private String duplicationEmailTemplate;

    /*邮件模板内容*/
    @JsonProperty(value = "email_template_content")
    private String emailTemplateContent;

}
