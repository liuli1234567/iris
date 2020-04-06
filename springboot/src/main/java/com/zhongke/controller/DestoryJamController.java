package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.DestoryJam;
import com.zhongke.pojo.Jam;
import com.zhongke.service.DestoryJamService;
import com.zhongke.service.JamService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName JamController
 * @Description 卡卷
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "核销卡卷相关")
@RestController
@RequestMapping("/destory")
public class DestoryJamController {

    private final Logger log = LoggerFactory.getLogger(DestoryJamController.class);

    @Autowired(required = false)
    private DestoryJamService destoryJamService;

    @PostMapping("/destoryJams/{page}/{size}")
    public Result<PageInfo> destoryJams(@RequestBody(required = false) DestoryJam destoryJam, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<DestoryJam> destoryJams = destoryJamService.destoryJams(destoryJam,page,size);
            return new Result<>(0,"查询成功",destoryJams);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("DestoryJamController.destoryJams(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

}