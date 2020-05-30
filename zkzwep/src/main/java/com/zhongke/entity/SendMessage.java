package com.zhongke.entity;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName SendMessage
 * @Description 向公众号发送通知
 * @Author liuli
 * @Date 2020/5/26 16:11
 * @Version 1.0
 **/
@Component
public class SendMessage {

    private final Logger logger = LoggerFactory.getLogger(SendMessage.class);

    // 审核成功模板id
    @Value("${audit.success}")
    private String auditSuccess;
    // 异常提醒模板id
    @Value("${exception}")
    private String exception;
    // 合同签署成功通知模板id
    @Value("${contract.success}")
    private String contractSuccess;
    // 接单成功模板id
    @Value("${order.success}")
    private String orderSuccess;

    public boolean sendDataTrueMessage(String access_token,String openid,String url){
        logger.info("开始发送资料审核通过通知");
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", auditSuccess);//模板消息id
            map.put("url",url);//用户点击模板消息，要跳转的地址
            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "资料审核通过！"); //内容
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
            CONTENTS.put("value",new Random().nextInt(900000000)+1000000000);
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("remark", XM);
            dataMap.put("keyword2", KH);
            dataMap.put("keyword1", CONTENTS);
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
            logger.info("发送资料审核通过通知成功！");
            return true;
        } catch (IOException e) {
            logger.error("发送资料审核通过通知出现错误！");
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendDataFalseMessage(String access_token,String openid){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", exception);//模板消息id
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
            CONTENTS.put("value", "资料错误或不完整");
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("keyword1", CONTENTS);
            dataMap.put("keyword2", KH);
            dataMap.put("remark", XM);

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

    public boolean sendContractTrueMessage(String access_token,String openid){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", auditSuccess);//模板消息id

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "合同审核通过通知！"); //内容
            firstMap.put("color", "#0099FF"); //字体颜色
            String newname;
            try {
                newname = (java.net.URLDecoder.decode("无", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                newname = "无";
            }

            // 封装keyword1 提交的问题
            Map XM = new HashMap();
            XM.put("value", newname);
            XM.put("color", "#0099FF");

            // 封装keyword2此处也可以是商品名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map KH = new HashMap();
            KH.put("value", "时间："+simpleDateFormat.format(date));
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", "无");
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", XM);
            dataMap.put("keyword1", CONTENTS);
            dataMap.put("keyword2", KH);
            dataMap.put("remark", XM);

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

    public static void main(String[] args) {
        System.out.println("");
    }
    public boolean sendContractFalseMessage(String access_token,String openid){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", exception);//模板消息id

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
            CONTENTS.put("value", "提交的合同有误");
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("keyword1", CONTENTS);
            dataMap.put("keyword2", KH);
            dataMap.put("remark", XM);

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

    public boolean sendOrderFalseMessage(String access_token,String openid){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", exception);//模板消息id

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
            CONTENTS.put("value", "打款有误");
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#fff");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", firstMap);
            dataMap.put("keyword1", CONTENTS);
            dataMap.put("keyword2", KH);
            dataMap.put("remark", XM);

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

    public boolean sendOrderTrueMessage(String access_token,String openid,String url){
        String getUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        try {
            // 封装要发送的json
            Map<String, Object> map = new HashMap();
            map.put("touser", openid);
            map.put("template_id", orderSuccess);//模板消息id
            map.put("url",url);//用户点击模板消息，要跳转的地址

            // 封装first
            Map firstMap = new HashMap();
            firstMap.put("value", "收款成功！"); //内容
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
            KH.put("value", "中科智王");
            // KH.put("color", "#173177");
            KH.put("color", "#0099FF");
            // 封装keyword3此处可以是商品价格
            Map CONTENTS = new HashMap();
            CONTENTS.put("value", new Random().nextInt(900000000)+1000000000);
            CONTENTS.put("color", "#0099FF");


            Map remarkMap = new HashMap();
            remarkMap.put("value", "欢迎下次光临！");
            remarkMap.put("color", "#0099FF");

            Map tel = new HashMap();
            tel.put("value", "0755-86576809");
            tel.put("color", "#0099FF");

            // 封装data
            Map dataMap = new HashMap();
            dataMap.put("first", XM);
            dataMap.put("keyword1", CONTENTS);
            dataMap.put("keyword2", KH);
            dataMap.put("keyword3", tel);
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
