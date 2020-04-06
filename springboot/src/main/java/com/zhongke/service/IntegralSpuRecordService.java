package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.IntegralSpuRecord;

public interface IntegralSpuRecordService {
    PageInfo<IntegralSpuRecord> record(IntegralSpuRecord integralSpuRecord, int page, int size);
}
