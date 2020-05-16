package com.zhongke.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.zhongke.mapper.DeviceMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Order;
import com.zhongke.service.AlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AlipayServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/4/22 11:32
 * @Version 1.0
 **/
@Service
public class AlipayServiceImpl implements AlipayService {
    private final Logger log = LoggerFactory.getLogger(AlipayService.class);
    private final AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2019111269065681","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=","json","UTF-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB","RSA2");
    private final String APP_ID = "2019111269065681";
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB";
    private final String CHARSET = "UTF-8";

    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private DeviceMapper deviceMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

    @Override
    public Map create_pay(String auth_code,String out_trade_no,String total_amount,String device_no) {
        try {
            System.out.println(auth_code);
            System.out.println(out_trade_no);
            System.out.println(device_no);
            AlipayTradePayRequest request = new AlipayTradePayRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            //sign	String	是	344	商户请求参数的签名串，详见签名	详见示例
            requestMap.put("out_trade_no", out_trade_no);
            requestMap.put("scene", "bar_code");
            requestMap.put("auth_code", auth_code);
            requestMap.put("subject", "iphone12");
            requestMap.put("total_amount", total_amount);
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradePayResponse response = alipayClient.execute(request,"",app_auth_token);
            HashMap<String, Object> map = new HashMap<>();
            map.put("response",response);
            map.put("device_no",device_no);
            // 将回调数据发送mq
            rabbitTemplate.convertAndSend(env.getProperty("mq.pay.exchange.alipayorder"),env.getProperty("mq.pay.routing.alipaykey"),map);
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map pay_query(String out_trade_no) {
        try {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeQueryResponse response = alipayClient.execute(request,"",app_auth_token);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
            System.out.println(response.getBody());
            HashMap<String, Object> map = new HashMap<>();
            map.put("response",response);
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map pay_cancel(String out_trade_no,String device_no) {
        try {
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeCancelResponse response = alipayClient.execute(request,"",app_auth_token);
            if(response.isSuccess()){
                //修改订单状态
                Order order = orderMapper.findOrderByOrderId(out_trade_no);
                order.setStatus(-1); // 订单支付状态改为：已取消
                orderMapper.updateByPrimaryKeySelective(order);
            } else {
                log.info("订单撤销失败：{},{}",response.getSubCode(),response.getSubMsg());
            }
            System.out.println(response.getBody());
            HashMap<String, Object> map = new HashMap<>();
            map.put("response",response);
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map pay_close(String out_trade_no) {
        try {
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeCloseResponse response = alipayClient.execute(request,"",app_auth_token);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
            System.out.println(response.getBody());
            HashMap<String, Object> map = new HashMap<>();
            map.put("response",response);
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map pay_refund(String out_trade_no, String refund_amount,String device_no) {
        try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            requestMap.put("refund_amount",refund_amount);
            JSON json = (JSON) JSON.toJSON(requestMap);
            request.setBizContent(json.toJSONString());
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeRefundResponse response = alipayClient.execute(request,"",app_auth_token);
            HashMap<String, Object> map = new HashMap<>();
            map.put("response",response);
            map.put("device_no",response);
            // 将回调数据发送mq
            rabbitTemplate.convertAndSend(env.getProperty("mq.pay.exchange.alirefundorder"),env.getProperty("mq.pay.routing.alirefundkey"),map);
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void saveOrder(String device_no, AlipayTradeRefundResponse response) {
        //保存订单数据
        Order order = new Order();
        order.setOrderId(response.getOutTradeNo());// 订单号
        order.setOrderAmount(new BigDecimal(response.getRefundFee()));// 退款总金额
        order.setBuyerLogonId(response.getBuyerLogonId());// 买家支付宝账号
        order.setBuyerUserId(response.getBuyerUserId()); // 买家userID
        order.setStatus(-2); // 设置支付状态为：退款
        order.setCode(response.getCode()); // 状态码
        order.setMsg(response.getMsg()); // 描述
        order.setSubCode(response.getSubCode()); // 错误码
        order.setSubMsg(response.getSubMsg()); // 错误描述
        order.setPayMethod("支付宝支付"); // 支付方式
        order.setPayAisle("支付宝"); // 支付通道
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundBuyerAmount() == null ? "0" : response.getPresentRefundBuyerAmount())); // 本次退款金额中买家退款金额
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundDiscountAmount() == null ? "0" : response.getPresentRefundDiscountAmount())); // 本次退款金额中平台优惠退款金额
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundMdiscountAmount() == null ? "0" : response.getPresentRefundMdiscountAmount())); // 本次退款金额中商家优惠退款金额
        order.setTransactionId(response.getTradeNo()); // 交易流水号
        order.setFundChannel(response.getRefundDetailItemList() == null ? null : response.getRefundDetailItemList().get(0).getFundChannel()); // 支付渠道
        order.setDeviceId(deviceMapper.findDeviceIdByDeviceNo(device_no)==null?0:deviceMapper.findDeviceIdByDeviceNo(device_no)); // 设备id
        order.setCashierId(1); // 收银员id
        order.setStoreName(response.getStoreName()); // 门店名称
        order.setUpdatetime(new Date()); // 更新时间
        order.setCreateTime(new Date()); // 创建时间
        order.setPayTime(response.getGmtRefundPay()); // 退款时间
        orderMapper.insertSelective(order);
    }

    public static void main(String[] args) {
        try {
            AlipayClient
                    alipayClient = new
                    DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                    "2019111269065681", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=", "json",
                    "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB", "RSA2");

            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            HashMap<String, String> map = new HashMap<>();
            map.put("grant_type","authorization_code");
            map.put("code","P51c929bcaa89462ab08c17c9aa66d28");
            request.setBizContent(JSON.toJSONString(map));
            AlipayOpenAuthTokenAppResponse
                    response = alipayClient.execute(request);
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    }
