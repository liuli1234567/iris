package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.IntegralSpu;
import com.zhongke.pojo.Jam;
import com.zhongke.service.IntegralSpuService;
import com.zhongke.service.JamService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IntegralSpuController
 * @Description 积分商品
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "卡卷相关")
@RestController
@RequestMapping("/integralSpu")
public class IntegralSpuController {

    private final Logger log = LoggerFactory.getLogger(IntegralSpuController.class);

    @Autowired(required = false)
    private IntegralSpuService integralSpuService;

    @PostMapping("/spus/{page}/{size}")
    public Result<PageInfo> spus(@RequestBody(required = false) IntegralSpu integralSpu, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<IntegralSpu> spus = integralSpuService.spus(integralSpu,page,size);
            return new Result<>(0,"查询成功",spus);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralSpuController.spus(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody() IntegralSpu integralSpu){
        try {
            integralSpuService.add(integralSpu);
            return new Result<>(0,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralSpuController.add(): "+e.getMessage());
            return new Result<>(-1,"添加失败");
        }
    }
}