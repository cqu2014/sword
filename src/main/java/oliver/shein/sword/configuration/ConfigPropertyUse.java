package oliver.shein.sword.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 10:47
 */
@Data
@ConfigurationProperties(prefix = "spring.redis")
@Component
public class ConfigPropertyUse {
    private String host = "123445556";
    private String port = "qefefwef";
}
