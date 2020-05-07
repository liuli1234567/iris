package com.zhongke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName MyConfig
 * @Description
 * @Author liuli
 * @Date 2020/5/6 11:37
 * @Version 1.0
 **/
@Configuration
public class MyConfig {
    // 注入RestTemplate
    @Bean(name = "restTemplate")
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
