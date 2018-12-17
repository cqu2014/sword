package oliver.shein.sword;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@ServletComponentScan(basePackages = {"oliver.shein.sword.filter"})
@ConfigurationProperties(prefix="spring.datasource")
public class SwordApplication {
	private String url;
	private String username;
	private String password;

	@Bean
	public DataSource getDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);// 用户名
		dataSource.setPassword(password);// 密码
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(SwordApplication.class, args);
	}
}
