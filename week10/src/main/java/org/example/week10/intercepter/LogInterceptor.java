package org.example.week10.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@Component // 交给Spring容器托管
public class LogInterceptor implements HandlerInterceptor {

    // 存请求开始时间的属性名
    public static final String ATTR_START_MS = "interceptor.log.startMs";

    /**
     * 请求进入前触发（接口方法执行前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 记录请求开始时间
        request.setAttribute(ATTR_START_MS, System.currentTimeMillis());

        // 2. 打印请求进入日志
        log.info("[日志拦截器] 请求进入 path={}, method={}, ip={}, time={}",
                request.getRequestURI(),
                request.getMethod(),
                request.getRemoteAddr(),
                LocalDateTime.now());

        // 返回true：放行请求；返回false：拦截请求
        return true;
    }

    /**
     * 响应结束后触发（接口方法执行完）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 1. 取出请求开始时间
        Long startMs = (Long) request.getAttribute(ATTR_START_MS);
        // 2. 计算耗时
        long costMs = startMs != null ? System.currentTimeMillis() - startMs : -1L;

        // 3. 打印响应结束日志
        log.info("[日志拦截器] 响应结束 path={}, status={}, 耗时={}ms, time={}, ex={}",
                request.getRequestURI(),
                response.getStatus(),
                costMs,
                LocalDateTime.now(),
                ex != null ? ex.getMessage() : null);

        // 4. 如果有异常，打印警告日志
        if (ex != null) {
            log.warn("[日志拦截器] 请求处理异常", ex);
        }
    }
}