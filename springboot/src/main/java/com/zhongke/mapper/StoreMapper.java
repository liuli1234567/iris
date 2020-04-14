package com.zhongke.mapper;

import com.zhongke.pojo.Store;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StoreMapper extends Mapper<Store> {
    List<Store> findByMerchantId(int id);

}
