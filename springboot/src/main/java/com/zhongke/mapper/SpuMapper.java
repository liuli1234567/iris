package com.zhongke.mapper;

import com.zhongke.pojo.Spu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuMapper extends Mapper<Spu> {

    Spu selectBySpuId(Integer spuId);

    List<Spu> findByNameOrNo(String nameOrNo);
}
