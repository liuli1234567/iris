package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.IntegralSpu;
import com.zhongke.pojo.IntegralSpuRecord;
import com.zhongke.service.IntegralRecordService;
import com.zhongke.service.IntegralSpuRecordService;
import com.zhongke.service.IntegralSpuService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IntegralSpuRecordController
 * @Description 积分商品兑换记录
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "积分商品兑换相关")
@RestController
@RequestMapping("/igralSpuRecord")
public class IntegralSpuRecordController {

    private final Logger log = LoggerFactory.getLogger(IntegralSpuRecordController.class);

    @Autowired(required = false)
    private IntegralSpuRecordService integralSpuRecordService;

    @PostMapping("/record/{page}/{size}")
    public Result<PageInfo> record(@RequestBody(required = false) IntegralSpuRecord integralSpuRecord, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<IntegralSpuRecord> records = integralSpuRecordService.record(integralSpuRecord,page,size);
            return new Result<>(0,"查询成功",records);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntegralSpuRecordController.record(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }
}