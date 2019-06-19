package oliver.shein.sword.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/19 13:39
 */
@RestController
@Slf4j
public class WXController {
    private final String TOKEN = "a4d33d001c034";

    @ApiOperation(value = "调用微信小程序接口")
    @GetMapping(value = "/wx")
    public String test(@RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("echostr") String echostr){

        //将token、timestamp、nonce三个参数进行字典序排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //将三个参数字符串拼接成一个字符串进行sha1加密
        String myString = sha1(sortString);
        //开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (myString != null && myString != "" && myString.equals(signature)) {
            log.info("签名校验通过");

            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            log.error("签名校验失败");
            return "";
        }
    }

    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("SHA-1加密失败",e);
            e.printStackTrace();
        }
        return "";
    }

}
