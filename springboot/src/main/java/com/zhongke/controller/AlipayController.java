package com.zhongke.controller;


import com.zhongke.entity.Result;
import com.zhongke.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/create")
    public Result<Map> create(@RequestParam(name = "auth_code") String auth_code,
                              @RequestParam(name = "out_trade_no") String out_trade_no){
        Map map = alipayService.create_pay(auth_code,out_trade_no);
        if (map != null) {
            return new Result<>(0,"调用成功！",map);
        }else {
            return new Result<>(-1,"支付失败",map);
        }
    }
}
