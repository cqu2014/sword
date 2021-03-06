package oliver.shein.sword.controller;

import io.swagger.annotations.ApiOperation;
import oliver.shein.sword.configuration.ConfigPropertyUse;
import oliver.shein.sword.vo.DateTransferRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/11/2 10:58
 */
@RestController
@RequestMapping(value = "/test/property")
public class PropertiesTestController {
    @Resource
    private ConfigPropertyUse configPropertyUse;

    @GetMapping(value = "/get")
    public Object getValue(){
        return configPropertyUse.getHost()+":"+configPropertyUse.getPort();
    }

    @PostMapping(value = "/date")
    public Object changeDate(@RequestBody DateTransferRequest transferRequest){
        return transferRequest;
    }

    @ApiOperation(value = "shuting")
    @GetMapping(value = "ting")
    public String shuting() {
        return "请问你是我廷吗？";
    }
}
