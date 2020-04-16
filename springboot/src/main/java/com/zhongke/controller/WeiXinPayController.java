package com.zhongke.controller;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.Result;
import com.zhongke.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @ClassName WeiXinPayController
 * @Description 微信支付
 * @Author liuli
 * @Date 2020/4/9 18:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/weixin/pay")
public class WeiXinPayController {

    @Autowired
    private WeiXinPayService weiXinPayService;

    /**
     * @Description 获取刷脸支付的调用凭证
     * @author liuli
     * @date 2020/4/16 11:01
     * @param
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping(value = "/get_wxpayface_authinfo")
    public Result getWxpayfaceAuthinfo(@RequestParam String rawdata){
        Map<String,String> map = weiXinPayService.getWxpayfaceAuthinfo(rawdata);
        return new Result(0,"获取调用凭证成功！",map);
    }

    /**
     * @Description 创建刷脸支付
     * @author liuli
     * @date 2020/4/16 13:40
     * @param openid 用户唯一标识
     * @param face_code 人脸凭证
     * @param out_trade_no 订单号
     * @param total_fee 订单金额
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/create_face_pay")
    public Result createFacePay(@RequestParam String openid, @RequestParam String face_code,@RequestParam String out_trade_no,@RequestParam String total_fee){
        Map<String, String> map = weiXinPayService.createFacePay(openid, face_code,out_trade_no,total_fee);
        if (map == null){
            return new Result(-1,"调用刷脸支付接口失败");
        }else {
            return new Result(0,"调用刷脸支付接口成功！", map);
        }
    }

    /**
     * @Description 查询刷脸支付状态
     * @author liuli
     * @date 2020/4/16 15:31
     * @param out_trade_no 订单号或流水号
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping(value = "/face_pay_status_query")
    public Result queryStatus(@RequestParam String out_trade_no){
        Map<String,String> map = weiXinPayService.queryPayStatus(out_trade_no);
        if (map == null){
            return new Result(-1,"查询刷脸支付订单状态失败");
        }else {
            return new Result(0,"查询刷脸支付订单状态失败成功！", map);
        }
    }

    /**
     * @Description 微信刷脸支付订单退款
     * @author liuli
     * @date 2020/4/16 16:11
     * @param total_fee 订单金额
     * @param refund_fee 退款金额
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/face_pay_refund")
    public Result refund(@RequestParam String total_fee,@RequestParam String refund_fee){
        Map<String, String> map = weiXinPayService.facePayRefund(total_fee,refund_fee);
        if (map == null){
            return new Result(-1,"微信刷脸支付订单退款失败");
        }else {
            return new Result(0,"微信刷脸支付订单退款成功！", map);
        }
    }

    /**
     * @Description 微信刷脸支付订单退款查询
     * @author liuli
     * @date 2020/4/16 16:44
     * @param out_refund_no
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/face_pay_refund_query")
    public Result refund_query(@RequestParam String out_refund_no){
        Map<String, String> map = weiXinPayService.refund_query(out_refund_no);
        if (map == null){
            return new Result(-1,"微信刷脸支付订单退款查询失败");
        }else {
            return new Result(0,"微信刷脸支付订单退款查询成功！", map);
        }
    }

    /**
     * @Description 刷脸支付订单撤销
     * @author liuli
     * @date 2020/4/16 17:14
     * @param out_trade_no
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/face_pay_reverse")
    public Result payReverse(@RequestParam String out_trade_no){
        Map<String, String> map = weiXinPayService.reverse(out_trade_no);
        if (map == null){
            return new Result(-1,"微信刷脸支付撤销订单失败");
        }else {
            return new Result(0,"微信刷脸支付撤销订单成功！", map);
        }
    }
}
