package oliver.shein.sword.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/9 19:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskEmailTemplate {
    /*语言*/
    private String language;

    /*站点*/
    @JsonProperty(value = "site_uid")
    private List<String> siteUid;

    /*模板体*/
    private RiskMailContent content;
}