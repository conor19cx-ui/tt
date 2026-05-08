package org.example.week10.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("AuthFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1. 获取请求头
        String authToken = httpRequest.getHeader("Authorization");
        log.info("【认证过滤器】收到请求头Authorization：{}", authToken);

        // 2. 校验Token（处理空值）
        if (authToken != null && "ok".equals(authToken)) {
            log.info("【认证过滤器】Token校验通过，放行请求");
            chain.doFilter(request, response);
        } else {
            log.warn("【认证过滤器】Token无效，拒绝访问");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("text/plain;charset=UTF-8");
            httpResponse.getWriter().write("Unauthorized");
        }
    }

    @Override
    public void destroy() {
        log.info("AuthFilter 销毁");
    }
}