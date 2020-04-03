package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.SvRecord;
import com.zhongke.pojo.SvRule;
import com.zhongke.service.SvRecordService;
import com.zhongke.service.SvRuleService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SvRulesController
 * @Description 储值记录
 * @Author liuli
 * @Date 2020/4/3 11:33
 * @Version 1.0
 **/
@Api(value = "储值记录相关")
@RestController
@RequestMapping("/svRecord")
public class SvRecordController {

    private final Logger logger = LoggerFactory.getLogger(SvRecordController.class);

    @Autowired(required = false)
    private SvRecordService svRecordService;

    /**
     * @Description 查询储值规则列表
     * @author liuli
     * @date 2020/4/3 14:12
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo<com.zhongke.pojo.SvRule>>
     **/
    @PostMapping("/svRecords/{page}/{size}")
    public Result<PageInfo<SvRecord>> svRecords(@RequestBody(required = false) SvRecord svRecord,@PathVariable int page, @PathVariable int size){
        try {
            PageInfo<SvRecord> svRecords = svRecordService.svRecords(svRecord,page,size);
            return new Result<>(0,"查询成功",svRecords);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SvRecordController.svRecords(): "+e.getMessage());
            return new Result<>(-1,"查询失败："+e.getMessage());
        }
    }
}
