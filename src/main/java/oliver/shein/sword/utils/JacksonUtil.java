package oliver.shein.sword.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/7/2
 * @since
 */
public final class JacksonUtil {
    private static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    //设置转化特性
    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
    }

    /**
     * String to javaBean
     *
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> Optional<T> readValue(String jsonStr, Class<T> valueType){
        log.debug("readValue:input[{}],class={}",jsonStr,valueType.getName());
        if (StringUtils.isEmpty(jsonStr)){
            return Optional.empty();
        }
        try {
            T t = objectMapper.readValue(jsonStr, valueType);
            log.debug("readValue:output [{}]",t);
            return Optional.ofNullable(t);
        } catch (IOException e) {
           log.warn("readValue:input[{}]异常",jsonStr,e);
        }
        return Optional.empty();
    }


    public static void main(String[] args) {

    }
}
