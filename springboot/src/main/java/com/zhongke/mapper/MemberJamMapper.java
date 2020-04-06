package com.zhongke.mapper;

import com.zhongke.pojo.MemberJam;
import tk.mybatis.mapper.common.Mapper;

public interface MemberJamMapper extends Mapper<MemberJam> {

    int count(int jamId);

}
