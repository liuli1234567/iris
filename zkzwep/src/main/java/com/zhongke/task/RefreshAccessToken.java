package com.zhongke.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongke.entity.HttpClient;
import com.zhongke.mapper.AccessTokenMapper;
import com.zhongke.pojo.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 每隔90分钟刷新access_token
 * @author liuli
 * @date 2020/5/26 9:26
 * @param
 * @return
 **/
@Configuration
public class RefreshAccessToken {

    private final Logger log = LoggerFactory.getLogger(RefreshAccessToken.class);

    @Autowired(required = false)
    private AccessTokenMapper accessTokenMapper;

    /**
     * @Description 每隔90分钟刷新access_token
     * @author liuli
     * @date 2020/5/28 11:03
     * @param
     * @return void
     **/
    @Scheduled(cron = "0 0 0/3 * * ?")
    @Scheduled(cron = "0 30 1/3 * * ?")
    public void createMerchantTransactionTable(){
        try {
            // 创建HttpClient发送请求
            HttpClient httpClient = new HttpClient("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx0daf15591cb53918&secret=e524f315775a15650843dd9eb551e041");
            httpClient.setHttps(true);
            httpClient.get();
            // 处理响应数据
            String content = httpClient.getContent();
            Map<String,String> map = JSON.parseObject(content, Map.class);
            AccessToken token = new AccessToken();
            token.setId(1);
            token.setAccessToken(map.get("access_token"));
            accessTokenMapper.updateByPrimaryKeySelective(token);
            log.info("每隔90分钟刷新access_token成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
