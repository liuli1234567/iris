package com.zhongke.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.HttpClient;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Order;
import com.zhongke.service.WeiXinPayService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName WeiXinPayServiceImpl
 * @Description 微信支付
 * @Author liuli
 * @Date 2020/4/9 18:08
 * @Version 1.0
 **/
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    @Value("${weixin.appid}")
    private String appid;           // 微信公众账号或开放平台APP的唯一标识

    @Value("${weixin.partner}")
    private String partner;         // 商户号

    @Value("${weixin.partnerkey}")
    private String partnerkey;      // 商户密钥

    @Value("${weixin.notifyurl}")
    private String notifyurl;       // 回调地址

    private RabbitTemplate rabbitTemplate;

    private Environment env;

    @Autowired(required = false)
    private OrderMapper orderMapper;

    /**
     * @Description 查询支付状态
     * @author liuli
     * @date 2020/4/15 11:15
     * @param out_trade_no
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @Override
    public Map<String, String> queryPayStatus(String out_trade_no) {
        try {
            // 查询微信刷脸支付订单状态地址
            String url = "https://api.mch.weixin.qq.com/pay/facepayquery";

            // 封装查询接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                               // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                            // 商户号
            data.put("nonce_str", WXPayUtil.generateNonceStr());    // 随机字符串
            data.put("out_trade_no", out_trade_no);                 // 商户订单号

            // 将数据转成xml并签名
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey, WXPayConstants.SignType.MD5);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 处理响应数据
        return null;
    }

    /**
     * @Description 调用刷脸支付接口
     * @author liuli
     * @date 2020/4/16 11:59
     * @param openid
     * @param face_code
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @Override
    public Map<String, String> createFacePay(String openid,String face_code,String out_trade_no,String total_fee) {
        try {
            // 刷脸支付
            String url = "https://api.mch.weixin.qq.com/pay/facepay";
            // 封装刷脸支付接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                               // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                            // 商户号
            data.put("nonce_str", WXPayUtil.generateNonceStr());    // 随机字符串
            data.put("body", "中科智王");                           // 商品描述
            data.put("out_trade_no", out_trade_no);                 // 商户订单号
            data.put("total_fee", total_fee);                       // 商品支付金额
            data.put("spbill_create_ip", "127.0.0.1");              // 终端ip
            data.put("openid", openid);                             // openid 用户唯一标识
            data.put("face_code", face_code);                       // 人脸凭证
            // 将数据转成xml
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);   // 将xml转成map
            // 将回调数据发送mq
            rabbitTemplate.convertAndSend(env.getProperty("mq.pay.exchange.order"), env.getProperty("mq.pay.routing.key"), JSON.toJSONString(map));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 获取刷脸支付的调用凭证
     * @author liuli
     * @date 2020/4/16 11:02
     * @param
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @Override
    public Map<String, String> getWxpayfaceAuthinfo(String rawdata) {
        try {
            // 获取调用凭证地址
            String url = "https://payapp.weixin.qq.com/face/get_wxpayface_authinfo";
            // 封装调用凭证接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("store_id", "store_1");                              // 门店编号，唯一
            data.put("store_name", "中科智王西丽店");                     // 门店名称
            data.put("device_id", "device_1");                            // 设备编号
            data.put("rawdata", rawdata);                                 // 初始化数据
            data.put("appid", appid);                                     // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                                  // 商户号
            data.put("now", Long.toString(System.currentTimeMillis()/1000L)); // 当前时间，10位unix时间戳                                 // 商户号
            data.put("version","1");                                      // 版本号，固定为1
            data.put("sign_type","MD5");                                  // 签名类型
            data.put("nonce_str", WXPayUtil.generateNonceStr());          // 随机字符串
            // 将数据转成xml
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);         // 将xml转成map
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 微信刷脸支付订单退款
     * @author liuli
     * @date 2020/4/16 16:11
     * @param total_fee 订单金额
     * @param refund_fee 退款金额
     * @return com.zhongke.entity.Result
     **/
    @Override
    public Map<String, String> facePayRefund(String total_fee, String refund_fee) {
        try {
            String out_refund_no = DateUtil.getTime();
            // 刷脸支付订单退款接口
            String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
            // 封装退款接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                                      // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                                   // 商户号
            data.put("out_refund_no",out_refund_no);                       // 退款单号
            data.put("total_fee",total_fee);                               // 订单金额
            data.put("refund_fee",refund_fee);                             // 退款金额
            data.put("nonce_str", WXPayUtil.generateNonceStr());           // 随机字符串
            // 将数据转成xml
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey,WXPayConstants.SignType.MD5);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);         // 将xml转成map
            map.put("out_refund_no",out_refund_no);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 微信刷脸支付订单退款查询
     * @author liuli
     * @date 2020/4/16 16:44
     * @param out_refund_no
     * @return com.zhongke.entity.Result
     **/
    @Override
    public Map<String, String> refund_query(String out_refund_no) {
        try {
            // 刷脸支付订单退款接口
            String url = "https://api.mch.weixin.qq.com/pay/refundquery";
            // 封装退款接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                                      // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                                   // 商户号
            data.put("out_refund_no",out_refund_no);                       // 退款单号
            data.put("nonce_str", WXPayUtil.generateNonceStr());           // 随机字符串
            // 将数据转成xml
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);          // 将xml转成map
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 刷脸支付订单撤销
     * @author liuli
     * @date 2020/4/16 17:14
     * @param out_trade_no
     * @return com.zhongke.entity.Result
     **/
    @Override
    public Map<String, String> reverse(String out_trade_no) {
        try {
            // 刷脸支付订单退款接口
            String url = "https://api.mch.weixin.qq.com/secapi/pay/facepayreverse";
            // 封装退款接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                                      // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                                   // 商户号
            data.put("out_trade_no",out_trade_no);                         // 订单号
            data.put("nonce_str", WXPayUtil.generateNonceStr());           // 随机字符串
            // 将数据转成xml
            String signedXml = WXPayUtil.generateSignedXml(data, partnerkey);
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(strXML);           // 将xml转成map
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
