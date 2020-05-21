package com.zhongke.config;

import com.zhongke.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName JWTConfig
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:51
 * @Version 1.0
 **/
@Configuration
public class JWTConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/**/login/**");
    }
}
