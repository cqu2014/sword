package oliver.shein.sword.service.resttemplate.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.resttemplate.RpcPHPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/22
 * @since
 */
@Service
@Slf4j
public class RpcPHPServiceImpl implements RpcPHPService {
    @Value("${sword.php.host}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Object addLetter(Object object) {
        return restTemplate.postForObject(url, object,String.class);
    }
}
