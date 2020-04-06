package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Jam;
import com.zhongke.service.JamService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName JamController
 * @Description 卡卷
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "卡卷相关")
@RestController
@RequestMapping("/jam")
public class JamController {

    private final Logger log = LoggerFactory.getLogger(JamController.class);

    @Autowired(required = false)
    private JamService jamService;

    @PostMapping("/jams/{page}/{size}")
    public Result<PageInfo> jams(@RequestBody(required = false) Jam jam, @PathVariable int page,@PathVariable int size){
        try {
            PageInfo<Jam> jams = jamService.jams(jam,page,size);
            return new Result<>(0,"查询成功",jams);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("JamController.jams(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody() Jam jam){
        try {
            jamService.add(jam);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("JamController.jams(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }
}