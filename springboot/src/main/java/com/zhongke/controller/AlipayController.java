package com.zhongke.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AlipayController
 * @Description 支付宝支付
 * @Author liuli
 * @Date 2020/4/21 11:09
 * @Version 1.0
 **/
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
    AlipayTradePayRequest request = new AlipayTradePayRequest();

}
