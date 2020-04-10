package com.zhongke.service;

import java.util.Map;

public interface WeiXinPayService {

    /**
     * @Description 生成支付二维码
     * @author liuli
     * @date 2020/4/9 18:06
     * @param out_trade_no 商户订单号
     * @param total_fee 支付金额
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String, String> createNative(String out_trade_no, String total_fee);

}
