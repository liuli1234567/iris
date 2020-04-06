package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.DestoryJam;
import com.zhongke.pojo.IntegralRecord;
import com.zhongke.service.DestoryJamService;
import com.zhongke.service.IntegralRecordService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IntegralRecordController
 * @Description 积分记录
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "积分记录")
@RestController
@RequestMapping("/integralRc")
public class IntegralRecordController {

    private final Logger log = LoggerFactory.getLogger(IntegralRecordController.class);

    @Autowired(required = false)
    private IntegralRecordService integralRecordService;

    @PostMapping("/integralRcs/{page}/{size}")
    public Result<PageInfo> integralRcs(@RequestBody(required = false) IntegralRecord integralRecord, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<IntegralRecord> integralRcs = integralRecordService.integralRcs(integralRecord,page,size);
            return new Result<>(0,"查询成功",integralRcs);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralRecordController.integralRcs(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

}