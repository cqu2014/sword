package oliver.shein.sword.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    /**
     * 设置转化特性
     * 遇未知属性报错
     * 允许单引号&&双引号
     */
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

    /**
     * string to list
     *
     * @param arrJson
     * @param contentClass
     * @return
     */
    public static <T> T readToList(String arrJson,Class contentClass){
        log.debug("readToList:input [{}],class={}",arrJson,contentClass.getName());
        if (StringUtils.isEmpty(arrJson)){
            return null;
        }
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
        try {
            T tList = objectMapper.readValue(arrJson,type);
            log.debug("readToList:output [{}]",tList);

            return tList;
        } catch (IOException e) {
            log.warn("readToList:input[{}]异常",arrJson,e);
        }

        return null;
    }

    /**
     * string to map<String,Class>
     * 根据contentClass获取类型信息 使用javaType 而非 TypeReference
     * @param mapString
     * @param <T>
     * @return
     */
    public static <T> T readToMap(String mapString,Class contentClass){
        log.debug("readToMap:input [{}],class={}",mapString,contentClass.getName());
        if (StringUtils.isEmpty(mapString)){
            return null;
        }
        JavaType type = objectMapper.getTypeFactory().constructParametricType(Map.class,String.class,contentClass);
        try {
            return objectMapper.readValue(mapString,type);
        }catch (Exception ex){
            return null;
        }
    }


    /**
     * string to list
     *
     * @param arrJson
     * @param contentClass
     * @return
     */
    public static <T> List<T> readToList1(String arrJson,Class contentClass){
        if (StringUtils.isEmpty(arrJson)){
            return null;
        }
        log.debug("readToList:input [{}],class={}",arrJson,contentClass.getName());
        //readValue使用javaType的重载方法
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
        try {
            List<T> tList = objectMapper.readValue(arrJson,type);
            log.debug("readToList:output [{}]",tList);

            return tList;
        } catch (IOException e) {
            log.warn("readToList:input[{}]异常",arrJson,e);
        }

        return null;
    }

    /**
     * string to list
     *
     * @param arrJson
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T readToList(String arrJson,TypeReference<T> valueTypeRef){
        log.debug("readToList:input [{}],class={}",arrJson,valueTypeRef.getType());
        if (StringUtils.isEmpty(arrJson)){
            return null;
        }
        T tList = null;
        try {
            tList = objectMapper.readValue(arrJson,valueTypeRef);
            log.debug("readToList:output [{}]",tList);
        } catch (IOException e) {
            log.warn("readToList:input[{}]异常",arrJson,e);
        }

        return tList;
    }

    public static String toJson(Object object){
        if (Objects.isNull(object)){
            return null;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
