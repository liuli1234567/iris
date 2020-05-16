package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Spu;

import java.util.List;

public interface SpuService {
    PageInfo<Spu> spus(String nameOrNo, int is_marketable, int page, int size);

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

    /**
     * @Description 商品批量导入
     * @author liuli
     * @date 2020/5/16 9:46
     * @param spus
     * @return void
     **/
    void addAll(List<Spu> spus);
}
