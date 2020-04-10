package com.zhongke.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.zhongke.entity.HttpClient;
import com.zhongke.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, String> createNative(String out_trade_no, String total_fee) {
        try {
            // 统一下单地址
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            // 封装支付接口需要的数据
            Map<String, String> data = new HashMap<>();
            data.put("appid", appid);                               // 微信公众账号或开放平台APP的唯一标识
            data.put("mch_id", partner);                            // 商户号
            data.put("nonce_str", WXPayUtil.generateNonceStr());    // 随机字符串
            data.put("body", "中科智王");                            // 商品描述
            data.put("out_trade_no", out_trade_no);                 // 商户订单号
            data.put("total_fee", total_fee);                       // 商品支付金额
            data.put("spbill_create_ip", "127.0.0.1");              // 终端ip
            data.put("notify_url", notifyurl);                      // 回调地址
            data.put("trade_type", "NATIVE");                       // 支付类
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
            map.put("out_trade_no", out_trade_no);
            map.put("total_fee", total_fee);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
