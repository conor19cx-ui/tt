package org.example.week10.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CustomFilter 过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("执行 CustomFilter 逻辑!");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("CustomFilter 接口执行完毕，返回处理");
    }

    @Override
    public void destroy() {
        log.info("CustomFilter 过滤器销毁");
    }
}