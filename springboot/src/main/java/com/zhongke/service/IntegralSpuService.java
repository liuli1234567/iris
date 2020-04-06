package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.IntegralSpu;

public interface IntegralSpuService {
    PageInfo<IntegralSpu> spus(IntegralSpu integralSpu, int page, int size);

    void add(IntegralSpu integralSpu);
}
