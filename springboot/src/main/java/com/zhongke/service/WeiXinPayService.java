package com.zhongke.service;

import java.util.Map;

public interface WeiXinPayService {

    /**
     * @Description 查询支付状态
     * @author liuli
     * @date 2020/4/15 11:15
     * @param out_trade_no
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String, String> queryPayStatus(String out_trade_no);

    /**
     * @Description 调用刷脸支付接口
     * @author liuli
     * @date 2020/4/16 11:59
     * @param openid
     * @param face_code
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String, String> createFacePay(String openid,String face_code,String out_trade_no,String total_fee);

    /**
     * @Description 获取刷脸支付的调用凭证
     * @author liuli
     * @date 2020/4/16 11:02
     * @param
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String, String> getWxpayfaceAuthinfo(String rawdata);

    /**
     * @Description 微信刷脸支付订单退款
     * @author liuli
     * @date 2020/4/16 16:11
     * @param total_fee 订单金额
     * @param refund_fee 退款金额
     * @return com.zhongke.entity.Result
     **/
    Map<String, String> facePayRefund(String total_fee, String refund_fee);

    /**
     * @Description 微信刷脸支付订单退款查询
     * @author liuli
     * @date 2020/4/16 16:44
     * @param out_refund_no
     * @return com.zhongke.entity.Result
     **/
    Map<String, String> refund_query(String out_refund_no);

    /**
     * @Description 刷脸支付订单撤销
     * @author liuli
     * @date 2020/4/16 17:14
     * @param out_trade_no
     * @return com.zhongke.entity.Result
     **/
    Map<String, String> reverse(String out_trade_no);
}
