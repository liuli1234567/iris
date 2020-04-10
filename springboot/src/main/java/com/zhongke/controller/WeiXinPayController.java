package com.zhongke.controller;

import com.zhongke.entity.Result;
import com.zhongke.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @Description 生成订单支付二维码
     * @author liuli
     * @date 2020/4/9 18:20
     * @param outtradeno 商户订单号
     * @param money 支付金额
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/create/native")
    public Result createNative(String outtradeno, String money){
        Map<String, String> map = weiXinPayService.createNative(outtradeno, money);
        return new Result(0,"创建支付二维码成功", map);
    }
}
