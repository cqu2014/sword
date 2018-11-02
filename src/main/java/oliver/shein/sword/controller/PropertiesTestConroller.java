package oliver.shein.sword.controller;

import oliver.shein.sword.configuration.ConfigPropertyUse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 10:58
 */
@RestController
@RequestMapping(value = "/test")
public class PropertiesTestConroller {
    @Resource
    private ConfigPropertyUse configPropertyUse;

    @GetMapping(value = "/get")
    public Object getValue(){
        return configPropertyUse.getHost()+":"+configPropertyUse.getPort();
    }
}
