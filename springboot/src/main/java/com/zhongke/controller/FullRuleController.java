package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.FullRule;
import com.zhongke.pojo.Jam;
import com.zhongke.service.FullRuleService;
import com.zhongke.service.JamService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FullRuleController
 * @Description 满减规则
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "满减规则")
@RestController
@RequestMapping("/fullRule")
public class FullRuleController {

    private final Logger log = LoggerFactory.getLogger(FullRuleController.class);

    @Autowired(required = false)
    private FullRuleService fullRuleService;

    @GetMapping("/fullRules/{page}/{size}")
    public Result<PageInfo> fullRules(@RequestParam(required = false) String startTime,
                                      @RequestParam(required = false) String endTime,
                                      @RequestParam(required = false) String activityStatus,
                                      @RequestParam(required = false) int status,
                                      @PathVariable int page,@PathVariable int size){
        try {
            System.out.println(startTime);
            PageInfo<FullRule> fullRules = fullRuleService.fullRules(startTime,endTime,activityStatus,status,page,size);
            return new Result<>(0,"查询成功",fullRules);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("FullRuleController.fullRules(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody() FullRule fullRule){
        try {
            fullRuleService.add(fullRule);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("FullRuleController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() FullRule fullRule,@PathVariable int id){
        try {
            fullRule.setId(id);
            fullRuleService.update(fullRule);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("FullRuleController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }
    }
}