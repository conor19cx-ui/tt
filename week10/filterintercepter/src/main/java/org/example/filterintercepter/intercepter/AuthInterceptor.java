package org.example.filterintercepter.intercepter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.filterintercepter.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器:登录认证 + 权限校验
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    /**
     * Controller方法执行前执行(核心逻辑)
     * @return true:放行 false:拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        log.info("【拦截器】进入请求:{}", uri);

        // 1. 放行登录接口
        if ("/api/login".equals(uri) || "/api/hello".equals(uri)) {
            return true;
        }
        // 2. 获取请求头Token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.warn("【拦截器】未登录,请求被拦截:{}", uri);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"请先登录\"}");
            return false;
        }
        // 3. 解析Token
        Claims claims = jwtUtil.parseToken(token);
        if (claims == null) {
            log.warn("【拦截器】Token无效/已过期:{}", uri);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"登录已过期,请重新登录\"}");
            return false;
        }
        // 4. 管理员接口权限校验
        String role = claims.get("role", String.class);
        if (uri.startsWith("/api/admin") && !"admin".equals(role)) {
            log.warn("【拦截器】权限不足,禁止访问管理员接口:{}", uri);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":403,\"msg\":\"权限不足,无法访问\"}");
            return false;
        }
        // 5. 把用户信息存入request,供Controller使用
        request.setAttribute("username", claims.get("username"));
        request.setAttribute("role", role);
        log.info("【拦截器】认证通过,用户:{},角色:{}", claims.get("username"), role);
        return true;
    }

    /**
     * Controller方法执行后,视图渲染前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("【拦截器】Controller执行完成");
    }

    /**
     * 视图渲染完成后执行(最后执行)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("【拦截器】请求完全处理完毕");
    }
}