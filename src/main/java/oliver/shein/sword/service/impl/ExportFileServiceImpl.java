package oliver.shein.sword.service.impl;

import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.MyLog;
import oliver.shein.sword.dto.Student;
import oliver.shein.sword.service.ExportFileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/3/25
 * @since
 */
@Service
@Slf4j
public class ExportFileServiceImpl implements ExportFileService {
    /**
     *  CSV文件分隔符(record之间)
     */
    private final static String NEW_LINE_SEPARATOR="\n";

    @Override
    @MyLog
    public void exportCsv(HttpServletResponse response, Long num) {
        List<Student> students = new LinkedList<>();
        while (num-- > 0) {
            students.add(
                    Student.builder()
                            .name("wangzhen".concat(String.valueOf(num)))
                            .sex(num % 2 == 0 ? "boy" : "girl")
                            .age(num)
                            .build()
            );
        }
        //初始化csvFormat
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try (PrintWriter fileWriter = response.getWriter();
                CSVPrinter printer=new CSVPrinter(fileWriter,csvFormat)) {
          printer.printRecord(Arrays.asList("姓名","性别","年龄"));
          if (!CollectionUtils.isEmpty(students)) {
              printer.printRecords(students.stream().map(Student::getAttribute).collect(Collectors.toList()));
          }
        } catch (IOException e) {
            log.warn("exportCsv异常{}",e);
        }
        log.info("exportCsv success");
    }
}
