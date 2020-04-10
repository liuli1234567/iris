package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Spu;

public interface SpuService {
    PageInfo<Spu> spus(String nameOrNo, int page, int size);

    void add(Spu spu);

    void delete(int id);

    void update(Spu spu);

    /**
     * @Description 批量操作：上下架，删除
     * @author liuli
     * @date 2020/4/9 14:45
     * @param spuIds
     * @param status
     * @return void
     **/
    void batch(String spuIds,int status);
}
