package com.zhongke.controller;

import com.alibaba.fastjson.JSON;
import com.zhongke.entity.HttpClient;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName GetOpenIdController
 * @Description 获取用户openid
 * @Author liuli
 * @Date 2020/5/29 15:23
 * @Version 1.0
 **/
@RestController
public class GetOpenIdController {
    @Value("${appid}")
    private String appid;
    @Value("${appsecret}")
    private String appsecret;

    @RequestMapping("/get_openid")
    public Result get_openid(HttpServletRequest request) throws Exception {
        try {
            System.out.println("回调方法执行了。。。");
            String code = request.getParameter("code");
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.get();
            // 处理响应数据
            String strXML = httpClient.getContent();
            Map<String,String> map = JSON.parseObject(strXML, Map.class);
            String openid = map.get("openid");
            return new Result(StatusCode.SUCCESS,"获取openid成功",openid);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(StatusCode.FALL,"获取openid失败");
        }
    }
}
