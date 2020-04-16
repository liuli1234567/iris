package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.AnnouncementMapper;
import com.zhongke.pojo.Announcement;
import com.zhongke.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AnnouncementServiceImpl
 * @Description 公告
 * @Author liuli
 * @Date 2020/4/2 14:14
 * @Version 1.0
 **/
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired(required = false)
    private AnnouncementMapper announcementMapper;


    @Override
    public PageInfo<Announcement> announs(String title, String startTime, String endTime, int page, int size) {
        List<Announcement> announcements = announcementMapper.announs(title,startTime,endTime);
        PageHelper.startPage(page,size);
        return new PageInfo<>(announcements);
    }
}
