package com.zhongke.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @ClassName PublicCallback
 * @Description 微信公众号回调
 * @Author liuli
 * @Date 2020/5/25 16:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/public")
public class PublicCallback {

    @RequestMapping("/notify/url")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        System.out.println("回调方法执行了。。。");
        String signature = request.getParameter("signature");
        System.out.println("signature:"+signature);
        System.out.println("timestamp"+request.getParameter("timestamp"));
        System.out.println("echostr"+request.getParameter("echostr"));
        String echostr = request.getParameter("echostr");
        return echostr;
    }
}
