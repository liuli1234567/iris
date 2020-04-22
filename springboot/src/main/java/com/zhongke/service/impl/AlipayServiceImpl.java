package com.zhongke.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.zhongke.service.AlipayService;
import org.springframework.stereotype.Service;

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

    @Override
    public Map create_pay(String auth_code,String out_trade_no) {
        try {
            System.out.println(auth_code);
            System.out.println(out_trade_no);
            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2019111269065681","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9qotPhEVpL92qUH7Jz1YfhXzERct5Vx595T5B9TDmM2fD+GBZ0vOjtiFEn9EHBixUERrE7nXD2BT13LysTAExCwxtKG/Orfyy5uaUukkXNMP5Vw45l4LhktpghiyY+59La3gr+uuXK4rzigOTiSWjhbElq4x/3IBM0QlInNuVhbIcLMD+bdBOvTuxET0gnUADMGUbmu0Rr2S5b8gPkY9P5vAJY3JNxduJpiNV3msNTfmUD9i1ifGLo+05AcpNNVBn6wgWkr1H/TXE/Sw6aaELvfYbCcHAzwTaol/lWwx+zsYNWYW40H9w+vZFKs7+UxD38WGAXfM1PzLfP2qza3tAgMBAAECggEAWaWeFIchry7v3Ve5QxI65an4LhhCSag3yZ0eVKJp93Hf9eM/OgoJO1Q3FQIPr9PsIk4O0XRL6kCJEJ8jC6u6GoYXxpGvIlR6DHAXAYcDBED2gwVIP1F8LHy1LWm/KzqmTSQUy6zeoz1P4amUVKVjb7jkANJR0vWkVUtC4qF5Jkf5craG2J3jr9ZGIiRE8IG3YuzU5rZ7fXb7zZCeuKxdKBEgW5Z1bm3/8usR5bhkqwp8Y+TPJALsZ9QqMRwrMeOb/LW4SN502/xG7i2FHre7ZQK4btxcALws2DX2MlmWAqIDMDCI/peDVDAa6rCEPDYvceH+afT414PGuRu0JFsRTQKBgQDMthMmSQuJjKHOGq+9NL3Y4i5DHzDIqiR/wfXletw+KsbQ6RaYYSJVR6XVMEzIYaWj1g3AROHIzmuQdJwrM4Eu1GrtJvJCDB6YDhCSzy1ln6Qqx8+Abk40Bcu+36yFlTJ8fhSNuY2EZE4LlbyhsYWHq5+3lXWvUAinYNc67p+UzwKBgQChRjn1l38Q1EkwT8D8t3+PX8EsWz/LJpN/ZXWLND+XcnFfWAR+zMbyAr8+3FidGTpUpa6uBckTyFNhUANHdoJdFjy8mX3OPemmOjNv7cRmoMW6fs7nvaz+F/SQDuYBPcFmrNp9dbyhjhcQ57lNgKk1sdaT8ES9Mg1MJTT9kET4gwKBgQCrjeKacRiUGyD2YnLocyyud04hvh/Z6oxP7LIvsDimeJ7JMK1Y4f4tza9x69pNC1gO5zH68T7uU97c3nyJz83w+t7pA3x+UT3KW+8TuT/oLFMd7vk8PjSXcEwBF92yzQfUQKzk4J1yV8T3/HJqZyTqP6/H4B8R3laCPqvgnV1rDQKBgH0cNrBgQ3GxzclNxaAHVkzKcthPPZECryAAfIxuDvsVDAB0DqCziY6LNQO1+oR84rRyB632zQOsv4pZgQG6XO2L57hDtsX9X7nVorutD1SyWlIQN8Ctc3t26AGtnR7PYq6dX64+XQRTb29s5GLMcfg2qwj/NsQzBP/SSWZ/uhGzAoGAGpEM9JTs9UGVJCmefiIMu+Fa14iUrvK81f05GnrqotpCM0lG96Xajk62ArsEwk45ThvHjMxCIY4o/gVsKkg8nWzu3bXi53if774DpKGteWfo7c2HCCX4StZ9bjiBUlENVD8jOF1A1hQ6jbw8bn2Fbb7xTPJKEVXA1AWvpF+WeEM=","json","UTF-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPnKdGFxWLNONtn+Dns3KDhVKGGfM6sSIvPYnKXLWg1pqHknZ+nNfzxG5QcUJg1tUODIVTE3Y66hcylYI1FEdsS0lfmim8SUwULuP/58JRsg/EVUEIsWYxz7zMcdsWUj8XNcy0eDlDbC0xak9SHcsCGHI5JtLWnpCFfFFlsrdA/wmzaxcviiE3Z1TJ16VDMgR9dwQmKkpek9L7o3VZN1LE+VTgbaPXsGDhloQMn4uqA3CMueuZAZZGYXSeDv0/SnODqtVHWGCigIlLOvLzQ+IbFmhDhVyZOR+sQvbnRoGnZFcfyLCaLw4l3MaNDh95rjMEAOVYeo36XMjTbu7/AjvQIDAQAB","RSA2");
            AlipayTradePayRequest request = new AlipayTradePayRequest();
            /*request.setBizContent("{" +
                    "\"out_trade_no\":\"20150320010101001\"," +
                    "\"scene\":\"bar_code\"," +
                    "\"auth_code\":\"28763443825664394\"," +
                    "\"subject\":\"Iphone6 16G\"," +

                    "  }");*/
            HashMap<String, String> requestMap = new HashMap<>();
            requestMap.put("out_trade_no",out_trade_no);
            requestMap.put("scene","bar_code");
            requestMap.put("auth_code",auth_code);
            requestMap.put("subject","Iphone6");
            String jsonString = JSON.toJSONString(requestMap);
            request.setBizContent(jsonString);
            String app_auth_token = "202004BBd64fc7c9a6344e7ea6fd1bc0ab090C28";
            AlipayTradePayResponse response = alipayClient.execute(request,"",app_auth_token);
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

    String a ="ErrorScene^_^40006^_^isv.self-invoke-forbidden^_^null^_^Windows 7^_^2020-04-22 14:54:06^_^ProtocalMustParams:" +
            "charset=UTF-8&method=alipay.trade.pay&sign=FqRUZSGUXsHcSK/prpsO2BKzzXCQ0IwI5jTVs0D8OqNW0EYzLFB7fhhvdfU6EBOBMxD8O" +
            "j0BQvRLSvy8mNWrorasrMd8m7/NMbi8DQle0pObYuUFB2MGXGpKMCI8G2BQHkGMh01OX9HsPuabljBV2+Y/DY0BrQSTUROlCdD4GptR8EkHirz" +
            "IFAJxGi8xk66210vFYgI+zF79t43AvGVnX7Z2dQbVf9WUhYFZbbvFas3/aMAdj35EU3cYm+alPW1LizUgKwI9mo/Mhglge6NjvdH+svHx+tYtr/y" +
            "U4Cr7Mz9KHbSqyGJoCDMI9AjufdxlkFZrZ5Wpf6F6Y75Upahb+Q==&version=1.0&app_id=2019111269065681&" +
            "sign_type=RSA2&timestamp=2020-04-22 14:54:05^_^ProtocalOptParams:alipay_sdk=alipay-sdk-java-dynamicVersionNo" +
            "&format=json^_^ApplicationParams:biz_content={\"out_trade_no\":\"2k345043252jj2\",\"subject\":\"Iphone6\",\"" +
            "scene\":\"bar_code\",\"auth_code\":\"faj134alsfk432\"}^_^Body:{\"alipay_trade_pay_response\":{\"code\":" +
            "\"40006\",\"msg\":\"Insufficient Permissions\",\"sub_code\":\"isv.self-invoke-forbidden\",\"sub_msg\":" +
            "\"此用户不允许自调用\"},\"sign\":\"djXycGo+qYlJQBMAeDtXU/e86nPJiJkCNmgNDbUe74v+2/" +
            "rsJeEfOZjQXh5U+amJgo9qhoJ8PojePiXS+bBXudd61H3O506FrFYwuzLCMbfJbny4RWpactm6UfTfAF+D+lyxslkvJvj" +
            "St256YhcDibmGT7VNntmtvsaz1eZSWvfMQnZtYhaMrc8qmydgIhRn1SKkjteMz5QkdySfkYcaIcRdAL55Rn5giUgmTX1PzWljKtsvX" +
            "aEElFKYen+BASCeUGq1fOJNJexfw+oyVhMC+8geROhgJLkO6+M2M1JsAaGrJI7Kim7pnCs2Sh2JsMz3zj75xmL5qGgeJhcVyhVbtg==\"}" +
            "^_^151ms,257ms,31ms";
    String msg2 = "{\"alipay_trade_pay_response\":{\"code\":\"20001\",\"msg\":\"Insufficient Token Permissions\",\"sub_code" +
            "\":\"aop.invalid-app-auth-token\",\"sub_msg\":\"无效的应用授权令牌\"},\"sign\":\"YN2" +
            "4Yz0GKb50iUAeDNGWVBcskaBSl6fK2vd/XdY6MOy4QFE+Nvq+6KaJi/wcIQ/Bu9ScNzbBqF7k2a2REE4a9Ymp79PSd+NCztPErx" +
            "FWQmrK8+jXdF2s5c4UQSHHNcoLqYIywEY4YCW8yUpBN7VZTfUPFr7LQMunb9Ur8FifuXe7z+WvvndV2V4eizgGvEWxg990iWmmI" +
            "WrOOc+NVu0t4gwbVgcaekIx9eqS/QwejJMl0ttFZ5d8mShVAbNHSnSFWYwYMHWBSX4KGj0fsFIdw0ycVWEVTNeHUt22yz/Hefjbl/plrv" +
            "FAXRf+tbHXz4EMHemUG4fDzdJCiw5AF7MPWw==\"}";

    String msg3 = "{\"alipay_trade_pay_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.ACCESS_FORBIDDEN" +
            "\",\"sub_msg\":\"支付失败，本商户没有权限使用该产品，请联系管理员处理，建议顾客使用其他方式付款。[ACCESS_FORBIDDEN]\",\"" +
            "buyer_pay_amount\":\"0.00\",\"invoice_amount\":\"0.00\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.00\"},\"" +
            "sign\":\"d2LSr/hJ1kuAb22fLawqla/uO3VTjR9Ul/5SZXegBUu4mMzMcuc0KPLCmTxxpvU+rx1sF97d72kyDmaFAyA/kSnylHezmHHT0Wdpk/" +
            "20YkFscwPqqyRLXY4IhziqqG8NEgU+0svytXqjuoFpcpUkKhhsruEbqzQ7BuFTVqHKpngXqDYDpNVcWba+CHWl4f9bhVkwHkgoFvmXEV/" +
            "HSNuBdWpnMK/EWWw6lG1Xypm8sKhbuL/yBjgrxFKghWXW9IDryWIj9LN3ndc00/" +
            "Cepvdxm7MyexPI7ne8pKfkMAbOpIKIhsP4a0n6Q43oFfBbp4rww70kCdFT1+/zwNAfso3tWw==\"}";

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
            map.put("code","Pb7e5dc643ec642e0a08d5278e14e128");
            request.setBizContent(JSON.toJSONString(map));
            AlipayOpenAuthTokenAppResponse
                    response = alipayClient.execute(request);
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
    }
