package com.zhongke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@CrossOrigin
@MapperScan(basePackages = "com.zhongke.mapper")
public class SpringbootDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo.class,args);
    }
    // 注入RestTemplate
    @Bean(name = "restTemplate")
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
