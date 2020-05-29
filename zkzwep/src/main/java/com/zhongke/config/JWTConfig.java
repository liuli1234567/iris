package com.zhongke.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName JWTConfig
 * @Description
 * @Author liuli
 * @Date 2020/5/28 10:06
 * @Version 1.0
 **/
@Configuration
public class JWTConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/**/login/**","/word/**","/images/**","/auditForm/add","/auditForm/findByOpenid",
                        "/contract/public_add","/contract/public_download","/order/pickup_code_query","/order/findByOpenid",
                        "/order/findById","/order/findOutById","/classpath:/static/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/static/");
        super.addResourceHandlers(registry);
    }
}