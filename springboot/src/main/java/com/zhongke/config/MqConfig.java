package com.zhongke.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @ClassName MqConfig
 * @Description
 * @Author liuli
 * @Date 2020/4/29 11:44
 * @Version 1.0
 **/
@Configuration
public class MqConfig {
    @Autowired
    private Environment environment;

    // 创建微信支付队列
    @Bean
    public Queue orderQueue(){
        return new Queue(environment.getProperty("mq.pay.queue.weixinorder"),true);
    }

    // 创建微信支付交换机
    @Bean
    public Exchange orderExchange(){
        return new DirectExchange(environment.getProperty("mq.pay.exchange.weixinorder"),true,false);
    }

    // 队列绑定到交换机
    @Bean
    public Binding bindQueueToExchange(Queue orderQueue, Exchange orderExchange){
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(environment.getProperty("mq.pay.routing.weixinkey")).noargs();
    }

    // 创建支付宝队列
    @Bean
    public Queue alipayOrderQueue(){
        return new Queue(environment.getProperty("mq.pay.queue.alipayorder"),true);
    }

    // 创建支付宝交换机
    @Bean
    public Exchange alipayOrderExchange(){
        return new DirectExchange(environment.getProperty("mq.pay.exchange.alipayorder"),true,false);
    }

    //将支付宝支付队列绑定到交换机
    @Bean
    public Binding bindQueueToExchangeForAlipay(Queue alipayOrderQueue,Exchange alipayOrderExchange){
        return BindingBuilder.bind(alipayOrderQueue).to(alipayOrderExchange).with(environment.getProperty("mq.pay.routing.alipaykey")).noargs();
    }

    // 创建支付宝退款队列
    @Bean
    public Queue alirefundOrderQueue(){
        return new Queue(environment.getProperty("mq.pay.queue.alirefundorder"),true);
    }

    // 创建支付宝退款交换机
    @Bean
    public Exchange alirefundOrderExchange(){
        return new DirectExchange(environment.getProperty("mq.pay.exchange.alirefundorder"),true,false);
    }

    //将支付宝退款队列绑定到交换机
    @Bean
    public Binding bindQueueToExchangeForAlirefund(Queue alirefundOrderQueue,Exchange alirefundOrderExchange){
        return BindingBuilder.bind(alirefundOrderQueue).to(alirefundOrderExchange).with(environment.getProperty("mq.pay.routing.alirefundkey")).noargs();
    }
}
