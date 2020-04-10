package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.IndustryCategory;
import com.zhongke.pojo.Store;
import com.zhongke.service.IndustryCategoryService;
import com.zhongke.service.StoreService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName IndustryCategoryController
 * @Description 行业类别
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "行业相关")
@RestController
@RequestMapping("/industry")
public class IndustryCategoryController {

    private final Logger log = LoggerFactory.getLogger(IndustryCategoryController.class);

    @Autowired(required = false)
    private IndustryCategoryService industryCategoryService;

    @GetMapping("/industrys")
    public Result<List<IndustryCategory>> industrys(){
        try {
            List<IndustryCategory> industryCategories = industryCategoryService.industrys();
            return new Result<>(0,"查询成功",industryCategories);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IndustryCategoryController.industrys(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }
}