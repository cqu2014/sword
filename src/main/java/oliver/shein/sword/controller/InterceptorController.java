package oliver.shein.sword.controller;

import io.swagger.annotations.ApiOperation;
import oliver.shein.sword.annotation.Access;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/18 16:51
 */
@RestController
@RequestMapping(value = "/interceptor")
public class InterceptorController {
    /**
     * 配置注解权限, 允许身份为admin, 或者说允许权限为admin的人访问
     *
     * @return
     */
    @ApiOperation(value = "配置注解权限")
    @RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @Access(authorities = {"admin"})
    public String hello() {
        return "Hello, admin";
    }
}
