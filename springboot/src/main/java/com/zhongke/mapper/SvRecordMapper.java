package com.zhongke.mapper;

import com.zhongke.pojo.SvRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SvRecordMapper extends Mapper<SvRecord> {

    List<SvRecord> svRecords(SvRecord svRecord);

}
