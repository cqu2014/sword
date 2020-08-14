package oliver.shein.sword.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.3.3
 * @Date Create at 2020/8/14
 * @Since
 */

public interface IExportJsonExcelService {
    /**
     * json 传转excel
     * @param file
     */
    void coverJson2Excel(MultipartFile file) throws IOException;
}
