package com.zhongke.task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ClassName CreateTable
 * @Description 动态创建交易流水表
 * @Author liuli
 * @Date 2020/4/28 18:43
 * @Version 1.0
 **/
public class CreateTable {
    @Scheduled(cron = "0/10 * * * * ? ")
    public void createTable(){

    }
}
