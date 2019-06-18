package oliver.shein.sword;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Oliver
 */

@SpringBootApplication(scanBasePackages = {"oliver.shein.sword"})
@EnableConfigurationProperties
@EnableSwagger2Doc
public class SwordApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwordApplication.class, args);
	}
}
