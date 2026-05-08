package org.example.week10.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("==== 日志过滤器 LogFilter 初始化 ====");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("【日志过滤器】请求路径:{} 客户端IP:{}", req.getRequestURI(), req.getRemoteAddr());

        chain.doFilter(request, response);

        log.info("【日志过滤器】请求处理完毕，返回响应");
    }

    @Override
    public void destroy() {
        log.info("==== 日志过滤器 LogFilter 销毁 ====");
    }
}