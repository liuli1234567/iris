package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Store;

public interface StoreService {

    PageInfo<Store> stores(Store store, int page, int size);

    void add(Store store);

    void update(Store store);

    Store findById(int id);


}
