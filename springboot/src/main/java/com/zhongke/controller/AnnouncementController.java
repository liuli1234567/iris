package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Announcement;
import com.zhongke.service.AnnouncementService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    private final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);

    @Autowired(required = false)
    private AnnouncementService announcementService;

    /**
     * @Description 通过条件查询公告列表并分页
     * @author liuli
     * @date 2020/4/3 10:29
     * @param title 公告标题
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo>
     **/
    @GetMapping("/announs/{page}/{size}")
    public Result<PageInfo> announs(@RequestParam(value = "title",required = false) String title, @RequestParam(value = "startTime",required = false)
            String startTime, @RequestParam(value = "endTime",required = false) String endTime, @PathVariable("page") int page,@PathVariable("size") int size){
        try {
            PageInfo<Announcement> announcements = announcementService.announs(title,startTime+" 00:00:00",endTime+" 00:00:00",page,size);
            return new Result<>(0,"查询成功",announcements);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AnnouncementController.announs(): "+e.getMessage());
            return new Result<>(-1,"查询失败："+e.getMessage());
        }

    }
}
