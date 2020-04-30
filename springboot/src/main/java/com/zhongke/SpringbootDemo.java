package com.zhongke;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@CrossOrigin // 跨域访问
@EnableScheduling // 开启定时任务
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
