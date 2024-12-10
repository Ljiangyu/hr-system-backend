package com.lss.hrbackend.config;

import com.lss.hrbackend.common.interceptor.UserRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.PipedReader;

/**
 * @author lss
 * @description
 * @createDate 2024/12/6-14:18
 */
@Configuration
public class UserConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserRequestHandler userRequestHandler;
    @Override
    @Order(0)
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userRequestHandler);
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 所有接口
//                .allowedOrigins("http://localhost:9528")  // 允许的跨域请求源
//                .allowedMethods("*")  // 允许的方法
//                .allowedHeaders("*")  // 允许的请求头
//                .allowCredentials(true)  // 允许发送凭证（如 cookies）
                .maxAge(3600);  // 预检请求的缓存时间
    }
}
