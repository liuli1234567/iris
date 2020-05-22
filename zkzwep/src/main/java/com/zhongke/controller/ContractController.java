package com.zhongke.controller;

import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ContractController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired(required = false)
    private ContractService contractService;

    /**
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @param openid 用户openid
     * @param userContract 只有客户章合同
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/add")
    public Result findAll(@RequestParam String openid,@RequestParam String userContract){
        contractService.add(openid,userContract);
        return new Result(StatusCode.SUCCESS,"添加成功");
    }
}
