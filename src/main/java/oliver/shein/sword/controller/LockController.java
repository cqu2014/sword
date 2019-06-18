package oliver.shein.sword.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import oliver.shein.sword.annotation.MyLog;
import oliver.shein.sword.service.IMsService;
import oliver.shein.sword.service.impl.HanoiThread;
import oliver.shein.sword.service.impl.MSThread;
import oliver.shein.sword.utils.Generator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/16 14:28
 */
@RestController
@Api(value = "swagger",tags = "swagger",description = "swagger")
@RequestMapping(value = "/lock")
@Slf4j
public class LockController {
    @Resource
    IMsService iMsService;

    @Resource
    RedisTemplate<String, Integer> redisTemplate;


    @ApiOperation(value="汉诺塔",notes="汉诺塔测试")
    @GetMapping(value = "/hanoi")
    public Object hanoi(){
        int n = 50;
        while(n-->0){
            new Thread(new HanoiThread(n)).start();
            log.info("第"+n+"个线程被启动");
        }
        return "线程跑汉诺塔完成！";
    }

    @GetMapping(value = "/sleep")
    public Object getlock(){
        int n = 50;
        while (n-->0){
            log.info("第"+n+"个线程启动");
            new Thread(new HanoiThread(n)).start();
        }
        return "Over";
    }

    @PostMapping(value = "/mylog")
    @MyLog(value = "我是注解小天")
    public String testLog(@RequestParam("name") String name){
        System.out.println("parameter: name = "+name);
        return "My Log is used here!";
    }

    @RequestMapping("/filter")
    public void modify(@RequestParam("name") String name,@RequestParam("oliver")String oliver){
        System.out.println("修改之后： " + name);
        System.out.println("修改之后： " + oliver);
    }

   @GetMapping(value = "/ms")
    public void msBegin(){
        for (int i = 0; i < 1000; i++) {
            MSThread threadA = new MSThread(iMsService,Generator.generateTaskId(),"oliver");
            new Thread(threadA).start();
        }
    }

     @GetMapping(value = "/product")
    public Object product(){
        redisTemplate.opsForValue().set("pronum",100);
        return redisTemplate.opsForValue().get("pronum");
    }

    @GetMapping(value = "ting")
    public String shuting(){
        return "请问你是我廷吗？";
    }

    public static void main(String[] args) {
        String result = null;

        result = "456";

        if (StringUtils.isEmpty(result)){
            System.out.println("123");
        }else {
            System.out.println(result);
        }
    }
}
