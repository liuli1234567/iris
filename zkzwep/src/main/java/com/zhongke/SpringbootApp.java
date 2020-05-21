package com.zhongke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName SpringbootApplication
 * @Description 启动类
 * @Author liuli
 * @Date 2020/5/19 10:37
 * @Version 1.0
 **/
@SpringBootApplication
@CrossOrigin // 跨域访问
@EnableScheduling // 开启定时任务
@MapperScan(basePackages = "com.zhongke.mapper")
public class SpringbootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApp.class,args);
    }
}
