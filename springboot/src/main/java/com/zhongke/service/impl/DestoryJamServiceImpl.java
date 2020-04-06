package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.DestoryJamMapper;
import com.zhongke.pojo.DestoryJam;
import com.zhongke.service.DestoryJamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName DestoryJamServiceImpl
 * @Description 核销卡卷
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Service
public class DestoryJamServiceImpl implements DestoryJamService {

    @Autowired(required = false)
    private DestoryJamMapper destoryJamMapper;

    @Override
    public PageInfo<DestoryJam> destoryJams(DestoryJam destoryJam, int page, int size) {
        PageHelper.startPage(page,size);
        if (destoryJam != null) {
            if (!StringUtils.isEmpty(destoryJam.getStartTime())) {
                destoryJam.setStartTime(destoryJam.getStartTime() + " 00:00:00");
            }
            if (!StringUtils.isEmpty(destoryJam.getEndTime())) {
                destoryJam.setEndTime(destoryJam.getEndTime() + " 23:59:59");
            }
            List<DestoryJam> destoryJams = destoryJamMapper.destoryJams(destoryJam);
            return new PageInfo<>(destoryJams);
        }else {
            return new PageInfo<>(destoryJamMapper.selectAll());
        }
    }
}