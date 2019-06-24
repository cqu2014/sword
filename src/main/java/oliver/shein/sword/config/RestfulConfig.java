package oliver.shein.sword.config;

import oliver.shein.sword.config.resttemplate.CustomRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/22
 * @since
 */
@Configuration
@EnableAsync
public class RestfulConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder(CustomRestTemplateCustomizer customRestTemplateCustomizer) {
        return new RestTemplateBuilder(customRestTemplateCustomizer);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .basicAuthorization("Oliver Wang","123456")
                .setConnectTimeout(6100)
                .setReadTimeout(5000)
                .build();
    }
}
