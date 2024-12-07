package com.lss.hrbackend.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.lss.hrbackend.common.userConfig.UserContext;
import com.lss.hrbackend.common.constant.HrConstant;
import com.lss.hrbackend.common.userConfig.UserInfo;
import com.lss.hrbackend.domain.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j(topic = "UserRequestHandler")
public class UserRequestHandler implements HandlerInterceptor {

    // ThreadLocal 用于保存当前线程的 Token
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的 URI
        String uri = request.getRequestURI();

        // 如果请求的 URI 是 login 或者 register，直接放行
        if (uri.contains("login") || uri.contains("register")) {
            return true;  // 放行，不拦截
        }

        // 其他请求需要拦截
        // 获取请求头中的 hr-token
        String token = request.getHeader(HrConstant.Web.REQUEST_HEAD);

        if (token == null || token.isEmpty()) {
            // 如果 token 为空，返回 401 错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access - hr-token is missing");
            return false;  // 拦截，返回错误响应
        }
        String userJson = redisTemplate.opsForValue().get(HrConstant.RedisKey.LOGIN_KEY + token);
        log.info("userjson ----------> {}",userJson);
        User user = JSON.parseObject(userJson, User.class);
        UserInfo userInfo = UserInfo.builder()
                .token(token)
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        // 将 token 设置到 ThreadLocal 中，供后续操作使用
        UserContext.setCurrentUser(userInfo);
        // 允许请求继续执行
        return true;
    }

    /**
     * 避免内存泄露
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理 ThreadLocal 中的 token
        UserContext.remove();
    }

    // 获取当前线程中的 Token
    public static String getCurrentToken() {
        return UserContext.getCurrentUser().getToken();
    }
}
