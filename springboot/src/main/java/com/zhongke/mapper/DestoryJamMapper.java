package com.zhongke.mapper;

import com.zhongke.pojo.DestoryJam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DestoryJamMapper extends Mapper<DestoryJam> {
    List<DestoryJam> destoryJams(DestoryJam destoryJam);
}
