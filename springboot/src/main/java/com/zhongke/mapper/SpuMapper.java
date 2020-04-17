package com.zhongke.mapper;

import com.zhongke.pojo.Spu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuMapper extends Mapper<Spu> {

    Spu selectBySpuId(Integer spuId);

    List<Spu> findByNameOrNo(@Param("nameOrNo") String nameOrNo,@Param("is_marketable") int is_marketable);
}
