package com.chen.demo.filter;

import com.alibaba.fastjson.JSON;
import com.chen.demo.common.JsonResult;
import com.chen.demo.enums.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//如果是这种方式需要去引导类写上@ServletComponentScan
@WebFilter(filterName = "userFilter",urlPatterns = "/*")
public class UserFilter implements Filter {
    public final Logger log = LoggerFactory.getLogger(UserFilter.class);
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取请求uri
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        //定义不需要处理请求路径
        String[] urls=new String[]{
                "/登录controller/login",
                "/登录controller/logout",
                "/静态资源/**",
                "/静态资源/**",
        };
        boolean check = check(urls, requestURI);
        if(check){
            log.info("本次请求不用处理:{}",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //判断登录状态
        if(request.getSession().getAttribute("登录后保存session")!=null){
            log.info("用户已登录，用户ID为：{}",request.getSession().getAttribute("登录后保存的session"));
            filterChain.doFilter(request, response);
            return;
        }
        //还未登录通过输出流，返回NOTLOGIN信息，前端响应拦截器进行页面跳转
        log.info("用户未登录！");
        response.getWriter().write(JSON.toJSONString(JsonResult.fail(ReturnCodeEnum.RC403.getCode(),ReturnCodeEnum.RC403.getMessage())));
    }

    /**
     * 路径匹配检查此次请求是否放行
     * @param urls
     * @param requestURI
     * @return
     */
    public static boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
