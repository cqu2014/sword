package oliver.shein.sword.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at
 */
@Component
@Data
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceConfigure {
    private String url;
    private String username;
    private String password;
}
