package com.zhongke.mq.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName Queue
 * @Description
 * @Author liuli
 * @Date 2020/4/29 11:16
 * @Version 1.0
 **/
/*@Component
public class Queue {
    @Autowired
    private Environment environment;

    // 创建队列
    @Bean
    public org.springframework.amqp.core.Queue AlipayOrderQueue(){
        return new org.springframework.amqp.core.Queue(environment.getProperty("mq.pay.queue.alipayorder"),true);
    }

    // 创建交换机
    @Bean
    public Exchange AlipayOrderExchange(){
        return new DirectExchange(environment.getProperty("mq.pay.exchange.alipayorder"),true,false);
    }

    // 队列绑定到交换机
    @Bean
    public Binding bindQueueToExchangeForAlipay( org.springframework.amqp.core.Queue alipayOrderQueue, Exchange alipayOrderExchange){
        return BindingBuilder.bind(alipayOrderQueue).to(alipayOrderExchange).with(environment.getProperty("mq.pay.routing.alipaykey")).noargs();
    }
}*/
