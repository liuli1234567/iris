package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.FullRule;
import com.zhongke.pojo.MembpaySetting;
import com.zhongke.service.FullRuleService;
import com.zhongke.service.MembpaySettingService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MembpaySettingController
 * @Description 会员支付设置
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员支付设置")
@RestController
@RequestMapping("/membpay")
public class MembpaySettingController {

    private final Logger log = LoggerFactory.getLogger(MembpaySettingController.class);

    @Autowired(required = false)
    private MembpaySettingService membpaySettingService;

    @GetMapping("/find")
    public Result<MembpaySetting> find(){
        try {
            MembpaySetting membpaySetting = membpaySettingService.find();
            return new Result<>(0,"查询成功",membpaySetting);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MembpaySettingController.find(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() MembpaySetting membpaySetting,@PathVariable int id){
        try {
            membpaySetting.setId(id);
            membpaySettingService.update(membpaySetting);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MembpaySettingController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }
    }
}