package com.zhongke.entity;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SendMessage
 * @Description 向公众号发送通知
 * @Author liuli
 * @Date 2020/5/26 16:11
 * @Version 1.0
 **/
public class SendMessage {

    public static boolean sendDataTrueMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv1AAc-WQYj0dMMkfH8dfKWg");
            map.put("template_id", "v05dFi0DIkWf2LUStOUlsFwnnWRXrhColvGXdIKOcAo");//模板消息id
            map.put("url",url);//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "资料审核通过通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("恭喜您！您的资料审核通过！请点击详情下载合同！", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "恭喜您！您的资料审核通过！请点击详情下载合同！";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            /*dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);*/

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setHttps(true);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendDataFalseMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv4MGnT0sNnJ8VbY-hDMeRi4");
            map.put("template_id", "t3ra6fwmeVkcKR6PifJcMMqXGAuQ_ik82V34a8gw5GA");//模板消息id
            //map.put("url","https://www.baidu.com");//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "资料审核驳回通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("很遗憾，您的资料未能审核通过，请联系客服查看原因。", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "很遗憾，您的资料未能审核通过，请联系客服查看原因。";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setHttps(true);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendContractTrueMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv4MGnT0sNnJ8VbY-hDMeRi4");//你要发送给某个用户的openid 前提是已关注该公众号,该openid是对应该公众号的，不是普通的openid
            map.put("template_id", "t3ra6fwmeVkcKR6PifJcMMqXGAuQ_ik82V34a8gw5GA");//模板消息id
            //map.put("url","https://www.baidu.com");//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "合同审核通过通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("恭喜您！您的合同审核通过！请查看合同收款账号打款！", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "恭喜您！您的合同审核通过！请查看合同收款账号打款！";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setHttps(true);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendContractFalseMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv4MGnT0sNnJ8VbY-hDMeRi4");//你要发送给某个用户的openid 前提是已关注该公众号,该openid是对应该公众号的，不是普通的openid
            map.put("template_id", "t3ra6fwmeVkcKR6PifJcMMqXGAuQ_ik82V34a8gw5GA");//模板消息id
           // map.put("url","https://www.baidu.com");//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "合同审核驳回通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("很遗憾，您的合同未能审核通过，请联系客服查看原因。", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "很遗憾，您的合同未能审核通过，请联系客服查看原因。";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setHttps(true);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendOrderFalseMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv4MGnT0sNnJ8VbY-hDMeRi4");
            map.put("template_id", "t3ra6fwmeVkcKR6PifJcMMqXGAuQ_ik82V34a8gw5GA");//模板消息id
            //map.put("url","https://www.baidu.com");//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "订单驳回通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("很遗憾，您的订单已被驳回，请联系客服查看原因。", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "很遗憾，您的订单已被驳回，请联系客服查看原因。";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setHttps(true);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendOrderTrueMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", "o0JYrv1AAc-WQYj0dMMkfH8dfKWg");
            map.put("template_id", "t3ra6fwmeVkcKR6PifJcMMqXGAuQ_ik82V34a8gw5GA");//模板消息id
            map.put("url",url);//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "收款成功通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("您的打款已收到！请点击详情查看取货码完成取货！期待下次光临！", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "您的打款已收到！请点击详情查看取货码完成取货！期待下次光临！";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "消费了" + 8888 + "元");
            CONTENTS.put("color", "#fff");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("XM", XM);
            dataMap.put("KH", KH);
            dataMap.put("CONTENTS", CONTENTS);
            dataMap.put("remark", remarkMap);

            map.put("data", dataMap);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(getUrl);
            httpClient.setXmlParam(JSON.toJSONString(map));
            httpClient.post();
            // 处理响应数据
            String content = httpClient.getContent();
            System.out.println(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
