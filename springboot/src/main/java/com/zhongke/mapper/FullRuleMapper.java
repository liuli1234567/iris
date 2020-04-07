package com.zhongke.mapper;

import com.zhongke.pojo.FullRule;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FullRuleMapper extends Mapper<FullRule> {
    List<FullRule> fullRules(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") int status);
}
