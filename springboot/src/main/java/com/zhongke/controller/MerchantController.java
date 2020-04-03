package com.zhongke.controller;

import com.zhongke.entity.Result;
import com.zhongke.service.MerchantService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName MerchantController
 * @Description 商户
 * @Author liuli
 * @Date 2020/4/1 18:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/merchant")
@Api(value = "商户相关")
public class MerchantController {

    private final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired(required = false)
    private MerchantService merchantService;

    @GetMapping("/getHomeByMerchantId")
    public Result<Map> getHomeByMerchantId(@RequestParam Integer merchantId, @RequestParam String startTime, @RequestParam String endTime) {
        try {
            Map<String, Object> map = merchantService.getHomeByMerchantId(merchantId, startTime, endTime);
            return new Result<>(0,"查询商户首页信息成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MerchantController.getHomeByMerchantId(): "+e.getMessage());
            return new Result<>(-1,"查询商户首页信息失败："+e.getMessage());
        }

    }
}
