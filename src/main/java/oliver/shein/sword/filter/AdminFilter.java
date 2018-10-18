package oliver.shein.sword.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/18 15:09
 */
@WebFilter(value = "/*")
public class AdminFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //允许进行跨域的主机IP
        response.setHeader("Access-Control-Allow-Origin","*");
        //允许访问的方法
        response.setHeader("Access-Control-Allow-Methods", "POST");
        // //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        filterChain.doFilter(request,response);
    }
}
