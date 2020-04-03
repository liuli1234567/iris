package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.SvRecord;

public interface SvRecordService {

    PageInfo<SvRecord> svRecords(SvRecord svRecord,int page, int size);
}
