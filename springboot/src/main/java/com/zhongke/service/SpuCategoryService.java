package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.SpuCategory;

public interface SpuCategoryService {
    PageInfo<SpuCategory> spuCategorys(int page, int size);

    void add(SpuCategory spuCategory);

    void delete(int id);

    void update(SpuCategory spuCategory);
}
