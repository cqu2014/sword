package oliver.shein.sword.config.resttemplate;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Oliver Wang
 * @description 对RestTemplate请求进行拦截并做相应处理
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/22
 * @since
 */
@Component("customRestTemplateCustomizer")
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setInterceptors(Collections.singletonList(new CustomClientHttpRequestInterceptor()));
    }
}
