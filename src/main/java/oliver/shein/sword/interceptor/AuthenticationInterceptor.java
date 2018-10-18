package oliver.shein.sword.interceptor;


import com.alibaba.druid.util.StringUtils;
import oliver.shein.sword.annotation.Access;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/18 15:58
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    /**
     * 在执行目标方法之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("preHandler");
        //将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //从方法处理器中获取要调用的方法
        Method method = handlerMethod.getMethod();
        //获取方法的Access注解
        Access access = method.getAnnotation(Access.class);
        if(access == null){
            return true; //直接放过
        }
        if(access.authorities().length > 0){
            //获取注解Access允许放行身份
            String[] authorities = access.authorities();
            Set<String> authSet = new HashSet<>();
            Arrays.stream(authorities).forEach(x->authSet.add(x));

            //获取请求者的角色
            String role = request.getParameter("role");
            if (!StringUtils.isEmpty(role)) {
                if (authSet.contains(role)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return false;
    }

    /**
     * 执行目标方法之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler");
    }

    /**
     * 在请求已经返回之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
