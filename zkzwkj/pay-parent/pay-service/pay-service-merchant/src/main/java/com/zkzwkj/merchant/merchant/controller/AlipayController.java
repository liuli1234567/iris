package com.zkzwkj.merchant.merchant.controller;

import com.zkzwkj.merchant.merchant.service.AlipayService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Map;

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
    @Autowired(required = false)
    private AlipayService alipayService;

    /**
     * @Description 支付宝统一收单交易支付接口
     * @author liuli
     * @date 2020/4/24 14:14
     * @param auth_code  付款码（baycde）
     * @param out_trade_no （订单号）
     * @param device_no （设备号）
     * @return com.zhongke.entity.Result<java.util.Map>
     **/
    @GetMapping("/create")
    public Result<Map> create(@RequestParam(name = "auth_code") String auth_code,
                              @RequestParam(name = "out_trade_no") String out_trade_no,
                              @RequestParam(name = "total_amount") String total_amount,
                              @RequestParam(name = "device_no")String device_no){
        Map map = alipayService.create_pay(auth_code,out_trade_no,total_amount,device_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"支付失败",map);
        }
    }

    /**
     * @Description 支付宝统一收单线下交易查询接口
     * @author liuli
     * @date 2020/4/24 15:38
     * @param out_trade_no 订单号
     * @return java.util.Map
     **/
    @GetMapping("/query")
    public Result<Map> query(@RequestParam(name = "out_trade_no") String out_trade_no){
        Map map = alipayService.pay_query(out_trade_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"查询失败",map);
        }
    }

    /**
     * @Description 支付宝统一收单交易退款接口
     * @author liuli
     * @date 2020/4/24 15:31
     * @param out_trade_no 订单号
     * @param refund_amount 退款金额
     * @param device_no 设备号
     * @return com.zhongke.entity.Result<java.util.Map>
     **/
    @GetMapping("/refund")
    public Result<Map> refund(@RequestParam(name = "out_trade_no") String out_trade_no,
                              @RequestParam(name = "refund_amount") String refund_amount,
                              @RequestParam(name = "device_no") String device_no){
        Map map = alipayService.pay_refund(out_trade_no,refund_amount,device_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"退款失败",map);
        }
    }


    /**
     * @Description 支付宝统一收单交易撤销接口
     * @author liuli
     * @date 2020/4/24 16:09
     * @param out_trade_no 订单号
     * @param device_no 设备号
     * @return com.zhongke.entity.Result<java.util.Map>
     **/
    @GetMapping("/cancel")
    public Result<Map> cancel(@RequestParam(name = "out_trade_no") String out_trade_no, @RequestParam(name = "device_no") String device_no){
        Map map = alipayService.pay_cancel(out_trade_no,device_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"撤销失败",map);
        }
    }

    /**
     * @Description 支付宝统一收单交易关闭接口
     * @author liuli
     * @date 2020/4/24 16:46
     * @param out_trade_no
     * @return com.zhongke.entity.Result<java.util.Map>
     **/
    @GetMapping("/close")
    public Result<Map> close(@RequestParam(name = "out_trade_no") String out_trade_no){
        Map map = alipayService.pay_close(out_trade_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"关闭失败",map);
        }
    }

    /**
     * @Description 商户授权回调接口，用于获取app_auth_code
     * @author liuli
     * @date 2020/4/24 14:32
     * @param request
     * @return java.lang.String
     **/
    @RequestMapping("/notify/url")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        System.out.println("回调方法执行了。。。");
        // 获取支付宝的通知信息
        ServletInputStream is = request.getInputStream();// 网络输入的字节流对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 数组：缓冲区
        byte[] buffer = new byte[1024];// 定义缓冲区的大小
        int len = 0;
        while ((len=is.read(buffer))!= -1){
            bos.write(buffer,0,len);
        }
        bos.flush();
        bos.close();
        is.close();

        // 将字节的输入流对象转成string
        String strXML = new String(bos.toByteArray(), "utf-8");
        System.out.println("通知的结果："+strXML);
        return "success";
    }
}
