package oliver.shein.sword.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.controller.request.UserInfo;
import oliver.shein.sword.core.ResponseVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/20
 * @since
 */
@RestController
@RequestMapping("/tools")
@Api(tags = "验证实用的工具")
@Slf4j
public class CheckToolsController {

    @ApiOperation("验证url编辑工具")
    @GetMapping("/uri/{author}")
    public ResponseVo<URI> uriComponent(@PathVariable String author, HttpServletRequest request){
        log.info("uriComponent: input data [author={},access_token={}]",author, request.getHeader("access_token"));
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://example.com/hotels/{hotel}/bookings/{booking}")
                .build();

        /**
         * expand()用于替换所有模板的变量
         * encode() 默认使用UTF-8
         * 注意：uriComponents是 immutable,expand()和encode()返回的都是新对象
         */
        URI uri = uriComponents.expand("MAX","ordering").encode().toUri();

        log.info("uri = {}",uri.toString());

        return ResponseVo.successWithInfo(uri);
    }

    @ApiOperation("获取Headers中的验证信息")
    @PostMapping("/uri/token")
    public ResponseVo<URI> uriComponent(@RequestBody @Valid UserInfo userInfo, HttpServletRequest request){
        log.info("uriComponent:input data [{}],access_token={}", JSON.toJSONString(userInfo),request.getHeader("access_token"));

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("oliver.cn").path("/hotels/{hotel}/bookings/{booking}").build()
                .expand("MAX","42")
                .encode();

        return ResponseVo.successWithInfo(uriComponents.toUri());
    }
}
