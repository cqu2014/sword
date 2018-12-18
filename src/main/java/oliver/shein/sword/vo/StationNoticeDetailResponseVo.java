package oliver.shein.sword.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/18 17:02
 */
@Data
public class StationNoticeDetailResponseVo {

    private String id;

    @JSONField(name = "site_from")
    private String siteFrom;

    private String title;

    private String operator;

    @JSONField(name = "start_time")
    private String startTime;

    @JSONField(name = "end_time")
    private String endTime;

    private String language;

    @JSONField(name = "sub_title")
    private String subTitle;

    private String content;
}
