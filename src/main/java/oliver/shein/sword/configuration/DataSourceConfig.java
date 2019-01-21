package oliver.shein.sword.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import oliver.shein.sword.configure.DataSourceConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    DataSourceConfigure dataSourceConfigure;

    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceConfigure.getUrl());
        // 用户名
        dataSource.setUsername(dataSourceConfigure.getUsername());
        // 密码
        dataSource.setPassword(dataSourceConfigure.getPassword());
        return dataSource;
    }
}
