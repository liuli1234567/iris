package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {

    PageInfo<Announcement> announs(String title, String startTime, String endTime, int page, int size);

}
