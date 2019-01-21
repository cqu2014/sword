package oliver.shein.sword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
/**
 * @author Oliver
 */

@SpringBootApplication
@ServletComponentScan(basePackages = {"oliver.shein.sword.filter"})
@EnableConfigurationProperties
public class SwordApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwordApplication.class, args);
	}
}
