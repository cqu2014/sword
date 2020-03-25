package oliver.shein.sword.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/3/25
 * @since
 */
public interface ExportFileService {
    /**
     * 导出csv文件
     * 
     * @param response
     * @param num
     */
    void exportCsv(HttpServletResponse response,Long num);
}
