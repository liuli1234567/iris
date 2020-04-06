package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.IntegralRecord;

public interface IntegralRecordService {
    PageInfo<IntegralRecord> integralRcs(IntegralRecord integralRecord, int page, int size);
}
