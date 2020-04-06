package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.IntegralRule;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.service.IntegralRuleService;
import com.zhongke.service.MemberGradeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IntegralRuleController
 * @Description 积分规则
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "积分规则相关")
@RestController
@RequestMapping("/integralRule")
public class IntegralRuleController {

    private final Logger log = LoggerFactory.getLogger(IntegralRuleController.class);

    @Autowired(required = false)
    private IntegralRuleService integralRuleService;

    @GetMapping("/find")
    public Result<IntegralRule> find(){
        try {
            IntegralRule integralRule = integralRuleService.find();
            return new Result<>(0,"查找成功",integralRule);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralRuleController.find(): "+e.getMessage());
            return new Result<>(-1,"查找失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody IntegralRule integralRule,@PathVariable int id){
        try {
            integralRule.setId(id);
            integralRuleService.update(integralRule);
            return new Result<>(0,"更新成功",integralRule);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralRuleController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }
    }
}