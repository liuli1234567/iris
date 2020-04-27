package com.zhongke.service;

import java.util.Map;

public interface AlipayService {
    /**
     * @Description 支付宝统一收单交易支付接口
     * @author liuli
     * @date 2020/4/24 14:20
     * @param auth_code
     * @param out_trade_no
     * @param total_amount
     * @param device_no
     * @return java.util.Map
     **/
    Map create_pay(String auth_code,String out_trade_no,String total_amount,String device_no);

    /**
     * @Description 支付宝统一收单交易退款接口
     * @author liuli
     * @date 2020/4/24 14:57
     * @param out_trade_no
     * @param refund_amount
     * @param device_no 设备号
     * @return java.util.Map
     **/
    Map pay_refund(String out_trade_no, String refund_amount,String device_no);

    /**
     * @Description 支付宝统一收单线下交易查询接口
     * @author liuli
     * @date 2020/4/24 15:38
     * @param out_trade_no 订单号
     * @return java.util.Map
     **/
    Map pay_query(String out_trade_no);

    /**
     * @Description 支付宝统一收单交易撤销接口
     * @author liuli
     * @date 2020/4/24 16:09
     * @param out_trade_no 订单号
     * @param device_no 设备号
     * @return java.util.Map
     **/
    Map pay_cancel(String out_trade_no,String device_no);

    /**
     * @Description 支付宝统一收单交易关闭接口
     * @author liuli
     * @date 2020/4/24 16:48
     * @param out_trade_no
     * @return java.util.Map
     **/
    Map pay_close(String out_trade_no);
}
