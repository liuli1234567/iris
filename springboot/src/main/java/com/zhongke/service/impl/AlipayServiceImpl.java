package com.zhongke.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.zhongke.entity.DateUtil;
import com.zhongke.service.AlipayService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
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
    private final AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2019111269065681","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=","json","UTF-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB","RSA2");
    private final String APP_ID = "2019111269065681";
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB";
    private final String CHARSET = "UTF-8";

    @Override
    public Map create_pay(String auth_code,String out_trade_no,String total_amount) {
        try {
            System.out.println(auth_code);
            System.out.println(out_trade_no);
            AlipayTradePayRequest request = new AlipayTradePayRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            //sign	String	是	344	商户请求参数的签名串，详见签名	详见示例
            requestMap.put("out_trade_no", out_trade_no);
            requestMap.put("scene", "bar_code");
            requestMap.put("auth_code", auth_code);
            requestMap.put("subject", "iphone12");
            //requestMap.put("goods_id", "202004231111");
            //requestMap.put("goods_name", "iphone14");
            //requestMap.put("quantity", 1);
            //requestMap.put("Price", 8000);
            requestMap.put("total_amount", total_amount);


            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradePayResponse response = alipayClient.execute(request,"",app_auth_token);
            // 根据response中的结果继续业务逻辑处理
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
    public Map pay_cancel(String out_trade_no) {
        try {
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeCancelResponse response = alipayClient.execute(request,"",app_auth_token);
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
    public Map pay_refund(String out_trade_no, String refund_amount) {
        try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("out_trade_no","222223");
            requestMap.put("refund_amount",2);
            JSON json = (JSON) JSON.toJSON(requestMap);
            request.setBizContent(json.toJSONString());
            //String app_auth_token = "202004BBd64fc7c9a6344e7ea6fd1bc0ab090C28";
            String app_auth_token = "201911BB960c3d24767f4a54b250bc45f5e19X18";
            AlipayTradeRefundResponse response = alipayClient.execute(request,"",app_auth_token);
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

    private AlipayTradePayResponse getAlipayTradePayResponse() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
        AlipayTradePayRequest request = new AlipayTradePayRequest(); //创建API对应的request类
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"auth_code\":\"289519743141099930\"," +//即用户在支付宝客户端内出示的付款码，使用一次即失效，需要刷新后再去付款
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"store_id\":\"NJ_001\"," +
                "    \"timeout_express\":\"2m\"," +
                "    \"total_amount\":\"0.01\"" +
                "  }"); //设置业务参数
        AlipayTradePayResponse response = alipayClient.execute(request,"","201911BB960c3d24767f4a54b250bc45f5e19X18"); //通过alipayClient调用API，获得对应的response类
        System.out.print(response.getBody());
        return response;
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
