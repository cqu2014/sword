package oliver.shein.sword.config.resttemplate;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/22
 * @since
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        response.getHeaders().add("headerName", "Oliver Wang");

        return response;
    }

    /**
     * 请求数据
     *
     * @param request
     * @param body
     */
    private void logRequest(HttpRequest request, byte[] body) {
        log.debug("*******************Request begin**********************");
        log.debug("URI:{}", request.getURI());
        log.debug("Method:{}", request.getMethod());
        log.debug("Headers:{}", request.getHeaders());
        log.debug("Body:[{}]", new String(body, Charsets.UTF_8));
        log.debug("*******************Request end**********************");
    }

    /**
     * 响应数据
     *
     * @param response
     * @throws IOException
     */
    private void logResponse(ClientHttpResponse response) throws IOException {
        log.debug("*******************response begin**********************");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.debug("*******************response end**********************");
    }
}
