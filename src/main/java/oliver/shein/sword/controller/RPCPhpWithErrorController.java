package oliver.shein.sword.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.service.resttemplate.RpcPHPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/22
 * @since
 */
@RestController
@Api("PHP返回非结构错误代码")
@RequestMapping("/response")
@Slf4j
public class RPCPhpWithErrorController {
    @Autowired
    RpcPHPService rpcPHPService;

    @ApiOperation("调用php后台接口")
    @GetMapping("/rpc")
    public Object request(@RequestBody Object object){
        log.info("request:input data [{}]",object);
        return rpcPHPService.addLetter(object);
    }
}
