package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.DestoryJam;


public interface DestoryJamService {
    PageInfo<DestoryJam> destoryJams(DestoryJam destoryJam,int page,int size);
}
