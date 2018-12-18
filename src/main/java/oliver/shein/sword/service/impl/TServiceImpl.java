package oliver.shein.sword.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import oliver.shein.sword.service.ITService;
import oliver.shein.sword.vo.StationNoticeDetailResponseVo;

import java.util.List;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/18 16:10
 */
public class TServiceImpl  implements ITService {
    @Override
    public <T> T toBean(String jsonData,  Class<T> valueType) {
        T responseList = JSON.parseObject(jsonData,valueType);

        return responseList;
    }

    @Override
    public <T> List<T> toList(String jsonData, Class<T> valueType) {
        List<T> tList = JSONArray.parseArray(jsonData,valueType);

        return tList;
    }

    public static void main(String[] args) {
        final String json = "[{\"site_from\":\"iosshom\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"en\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iosshom\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"fr\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iosshom\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"de\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iosshbh\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"en\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iosshbh\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"fr\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iosshbh\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"de\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iossheg\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"en\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iossheg\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"fr\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"},{\"site_from\":\"iossheg\",\"id\":\"10\",\"title\":\"宇宙上下五千年大促销\",\"operator\":\"银角大王\",\"start_time\":\"2999-12-21 13:50:48\",\"end_time\":\"2999-12-21 14:50:48\",\"language\":\"de\",\"sub_title\":\"黑色星期五年度大促销\",\"content\":\"井底点灯深烛伊，共郎长行莫围棋。玲珑骰子安红豆，入骨相思知不知\"}]";

        TServiceImpl tService = new TServiceImpl();

        List<StationNoticeDetailResponseVo> responseVoList = tService.toList(json,StationNoticeDetailResponseVo.class);

        System.out.println(responseVoList);
    }
}
