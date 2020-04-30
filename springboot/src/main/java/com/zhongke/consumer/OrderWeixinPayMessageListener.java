package com.zhongke.consumer;

import com.alibaba.fastjson.JSON;
import com.zhongke.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName OrderWeixinPayMessageListener
 * @Description 微信支付消息监听
 * @Author liuli
 * @Date 2020/4/17 14:19
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = {"${mq.pay.queue.weixinorder}"})
public class OrderWeixinPayMessageListener {

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void readMessage(String msg){
        Map<String,String> map = JSON.parseObject(msg, Map.class);
        // 通信标识
        String return_code = map.get("return_code");
        // 业务结果
        String result_code = map.get("result_code");
        String out_trade_no = map.get("out_trade_no"); // 商户订单号
        if ("SUCCESS".equals(return_code)){
            if ("SUCCESS".equals(map.get("result_code"))){
                // TODO 调用订单mapper把数据存库,支付状态为：已支付
                orderService.updateStatus(out_trade_no,map.get("transaction_id"),map.get("time_end"),1);
            }
            if ("FAIL".equals(map.get("result_code"))){
                // TODO 调用订单mapper把数据存库，支付状态为：支付失败
                orderService.updateStatus(out_trade_no,map.get("transaction_id"),map.get("time_end"),-3);
            }
        }
        if ("FAIL".equals(map.get("return_code"))){
            // TODO 调用订单mapper把数据存库，支付状态为：支付失败
            orderService.updateStatus(out_trade_no,map.get("transaction_id"),map.get("time_end"),-3);
        }
    }
}
