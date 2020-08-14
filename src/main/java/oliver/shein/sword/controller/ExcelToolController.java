package oliver.shein.sword.controller;

import cn.hutool.json.JSONUtil;
import cn.miludeer.jsoncode.JsonCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.controller.request.UserInfo;
import oliver.shein.sword.controller.response.ResponseVo;
import oliver.shein.sword.service.IExportJsonExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Oliver Wang
 * @description 处理excel文件
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/13
 * @since
 */
@Controller
@Api(value = "Excel 表格处理",tags = "excel-handler")
@RequestMapping("/excel")
@RequiredArgsConstructor
@Slf4j
public class ExcelToolController {

    private final IExportJsonExcelService iExportJsonExcelService;

    @ApiOperation(value = "导入json获取节点列表")
    @PostMapping(value = "/import_json")
    public ResponseVo<Object> importJson(@RequestPart("file") MultipartFile file, @RequestPart("user") UserInfo userInfo){
        log.info("userInfo = {}", JSONUtil.toJsonStr(userInfo));
        log.info("getOriginalFilename:{}",file.getOriginalFilename());
        log.info("getName:{}",file.getName());
        log.info("getContentType:{}",file.getContentType());
        String jsonString;
        try {
            jsonString = new String(file.getBytes());
        } catch (IOException e) {
           log.info("importJson IOException",e);
           throw new RuntimeException(e.getLocalizedMessage());
        }
        log.info("jsonString=[{}]",jsonString);
        // 获取jsonString中的
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(JsonCode.getValue(jsonString, "$.paths"));
        } catch (IOException e) {
            log.warn(" mapper.readTree IOException:",e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
        List<String> stringArrayList = Lists.newArrayList(jsonNode.fieldNames());
        log.info("jsonNode.fieldNames={}",JSONUtil.toJsonStr(stringArrayList));
        return ResponseVo.successWithInfo(stringArrayList);
    }

    @ApiOperation(value = "导入json文件导出Excel")
    @PostMapping("/import_json_excel")
    public ResponseVo<Object> importJson2Excel(@RequestPart("file") MultipartFile file, @RequestPart("user") UserInfo userInfo) throws IOException {
        log.info("userInfo = {}", JSONUtil.toJsonStr(userInfo));
        log.info("getOriginalFilename:{}",file.getOriginalFilename());
        log.info("getName:{}",file.getName());
        log.info("getContentType:{}",file.getContentType());
        iExportJsonExcelService.coverJson2Excel(file);
        return ResponseVo.successWithInfo("OK");
    }
}
