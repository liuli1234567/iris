package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Announcement;
import com.zhongke.service.AnnouncementService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AnnouncementController
 * @Description 公告
 * @Author liuli
 * @Date 2020/4/2 15:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/announ")
@Api(value = "公告相关")
public class AnnouncementController {

    private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired(required = false)
    private AnnouncementService announcementService;

    @GetMapping("/announs/{page}/{size}")
    public Result<PageInfo> announs(@RequestParam(value = "title",required = false) String title, @RequestParam(value = "startTime",required = false)
            String startTime, @RequestParam(value = "endTime",required = false) String endTime, @PathVariable("page") int page,@PathVariable("size") int size){
        try {
            PageInfo<Announcement> announcements = announcementService.announs(title,startTime,endTime,page,size);
            return new Result<>(0,"查询公告列表成功",announcements);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AnnouncementController.announs(): "+e.getMessage());
            return new Result<>(-1,"查询公告列表失败："+e.getMessage());
        }

    }
}
