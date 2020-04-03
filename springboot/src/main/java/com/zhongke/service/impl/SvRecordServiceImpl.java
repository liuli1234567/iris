package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.SvRecordMapper;
import com.zhongke.pojo.SvRecord;
import com.zhongke.service.SvRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SvRecordServiceImpl
 * @Description 储值记录
 * @Author liuli
 * @Date 2020/4/3 16:02
 * @Version 1.0
 **/
@Service
public class SvRecordServiceImpl implements SvRecordService {

    @Autowired(required = false)
    private SvRecordMapper svRecordMapper;

    @Override
    public PageInfo<SvRecord> svRecords(SvRecord svRecord,int page, int size) {
        PageHelper.startPage(page,size);
        List<SvRecord> svRecords = svRecordMapper.svRecords(svRecord);
        return new PageInfo<>(svRecords);
    }
}
