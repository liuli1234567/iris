package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Jam;
import com.zhongke.pojo.Store;

public interface JamService {

    PageInfo<Jam> jams(Jam jam, int page, int size);

    void add(Jam jam);

}
