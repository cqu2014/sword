package oliver.shein.sword.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.miludeer.jsoncode.JsonCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.IExportJsonExcelService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/14
 * @since
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ExportJsonExcelServiceImpl implements IExportJsonExcelService {
    /**
     * ObjectMapper
     */
    private static final  ObjectMapper mapper = new ObjectMapper();

    /**
     * 响应HttpServletResponse
     */
    private final HttpServletResponse response;

    @Override
    public void coverJson2Excel(MultipartFile file) throws IOException {
        /**
         * json串
         */
        String jsonString = new String(file.getBytes());
        log.info("jsonString=[{}]",jsonString);
        // 获取jsonString中的$.paths
        JsonNode jsonNode = mapper.readTree(JsonCode.getValue(jsonString, "$.paths"));
        /**
         * 接口paths-list
         */
        List<String> stringArrayList = Lists.newArrayList(jsonNode.fieldNames());
        log.info("jsonNode.fieldNames={}", JSONUtil.toJsonStr(stringArrayList));
        String fileOriginName = StringUtils.isEmpty(file.getOriginalFilename())?String.valueOf(System.currentTimeMillis()):file.getOriginalFilename();
        String filename =fileOriginName.substring(0,fileOriginName.indexOf(".")-1);
        // 待写入接口信息
        final List<List<String>> apiInfoList = new LinkedList<>();
        apiInfoList.add(CollUtil.newArrayList("接口名称","模块名称","请求方法","接口地址"));
        stringArrayList.forEach(x->{
            String method = getMethod(jsonString,x);
            String summary = JsonCode.getValue(jsonString,"$.paths."+x+"."+method+".summary");
            String[] valueList = JsonCode.getValueList(jsonString, "$.paths." + x + "." + method + ".tags");
            apiInfoList.add(CollUtil.newArrayList(summary,valueList[0].replaceAll("\\u0022",""),method,x));
        });
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/ucm_system/"+filename+".xlsx");
        // ExcelWriter writer = ExcelUtil.getWriter(true);
        //一次性写出内容，强制输出标题
        writer.write(apiInfoList, true);
       /* setResponse(response,filename);
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);*/
        //关闭writer，释放内存
        writer.close();
        // IoUtil.close(outputStream);
    }

    /**
     * 获取api method 字符串
     *
     * @param jsonString
     * @param key
     * @return
     */
    private String getMethod(String jsonString,String key){
        if (StringUtils.isEmpty(JsonCode.getValue(jsonString,"$.paths."+key+".post"))){
            return "get";
        }
        return "post";
    }

    /**
     * 设置响应属性
     *
     * @param response
     */
    private void setResponse(HttpServletResponse response,String systemName) throws UnsupportedEncodingException {
        //1.设置文件ContentType类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //response.setContentType("application/octet-stream");
        //2.设置文件头
        response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(systemName+".xlsx","UTF-8"));
    }
}
