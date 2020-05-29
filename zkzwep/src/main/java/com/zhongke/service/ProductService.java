package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Product;

import java.util.List;

public interface ProductService {
    /**
     * @Description 添加产品
     * @author liuli
     * @date 2020/5/21 10:25
     * @param productName 产品名称
     * @param stock_in  产品吨数
     * @param userId  操作人id
     * @return void
     **/
    void add(String productName, int stock_in,int userId);

    /**
     * @Description 产品出库
     * @author liuli
     * @date 2020/5/21 10:40
     * @param id 产品id
     * @param stock_out 出库吨数
     * @param userId  操作人id
     * @return int
     **/
    int update(int id, int stock_out,int userId);

    /**
     * @Description 产品列表查询
     * @author liuli
     * @date 2020/5/22 10:17
     * @param name 产品名
     * @param inputStartTime 入库起始时间
     * @param inputEndTime 入库结束时间
     * @param outStartTime 出库起始时间
     * @param outEndTime 出库结束时间
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Product>
     **/
    PageInfo<Product> findAll(String name, String inputStartTime,String inputEndTime,String outStartTime,String outEndTime,int page, int size);

    /**
     * @Description 产品入库
     * @author liuli
     * @date 2020/5/25 15:08
     * @param id 产品id
     * @param stock_in 入库吨数
     * @param userId 操作人id
     * @return void
     **/
    void in(int id, int stock_in, int userId);
}
