package oliver.shein.sword.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.ExportFileService;
import oliver.shein.sword.utils.Generator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Oliver Wang
 * @description 文件导出接口
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/3/25
 * @since
 */
@RestController
@Api(value = "文件导出",tags = "export-file")
@RequestMapping("/export")
@RequiredArgsConstructor
@Slf4j
public class ExportCsvController {
    private final ExportFileService exportFileService;

    @ApiOperation(value = "导出CSV文件")
    @GetMapping("/csv")
    void exportCSV(@ApiParam(value = "个数",required = true)
                   @RequestParam(value = "num",required = false) Long num, @RequestParam(value = "filename",required = false) String filename,HttpServletResponse response){
        String fileName = StringUtils.isEmpty(filename)? String.valueOf(Generator.generateTaskId()):filename;
        Long number = Objects.isNull(num)||num == 0L?10000L:num;
        //1.设置文件ContentType类型
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        //2.设置文件头
        response.setHeader("Content-Disposition", "attachment;fileName=".concat(fileName).concat(".csv"));
        //exportFileService.exportCsv(response,number);
        exportFileService.exportCsv(num);
    }
}
