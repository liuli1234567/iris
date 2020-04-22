package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.HttpClient;
import com.zhongke.entity.Result;
import com.zhongke.pojo.SvRule;
import com.zhongke.service.SvRuleService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SvRulesController
 * @Description 储值规则
 * @Author liuli
 * @Date 2020/4/3 11:33
 * @Version 1.0
 **/
@Api(value = "储值规则相关")
@RestController
@RequestMapping("/svRule")
public class SvRuleController {

    private final Logger logger = LoggerFactory.getLogger(SvRuleController.class);

    @Autowired(required = false)
    private SvRuleService svRulesService;

    /**
     * @Description 查询储值规则列表
     * @author liuli
     * @date 2020/4/3 14:12
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo<com.zhongke.pojo.SvRule>>
     **/
    @GetMapping("/svRules/{page}/{size}")
    public Result<PageInfo<SvRule>> svRules(@PathVariable int page, @PathVariable int size){
        try {
            PageInfo<SvRule> svRules = svRulesService.svRules(page,size);
            return new Result<>(0,"查询成功",svRules);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SvRuleController.svRules(): "+e.getMessage());
            return new Result<>(-1,"查询失败："+e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody SvRule svRule){
        try {
            svRulesService.add(svRule);
            return new Result(0,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SvRuleController.add(): "+e.getMessage());
            return new Result(-1,"添加失败："+e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        try {
            svRulesService.delete(id);
            return new Result(0,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SvRuleController.delete(): "+e.getMessage());
            return new Result(-1,"删除失败："+e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody SvRule svRule,@PathVariable int id){
        try {
            svRule.setId(id);
            svRulesService.update(svRule);
            return new Result(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SvRuleController.update(): "+e.getMessage());
            return new Result(-1,"更新失败："+e.getMessage());
        }
    }
}
