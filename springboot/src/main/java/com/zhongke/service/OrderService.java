package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * @Description 查询全部订单
     * @author liuli
     * @date 2020/4/1 10:06
     * @param
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    List<Order> findAll();

    /**
     * @Description 查询设备订单列表
     * @author liuli
     * @date 2020/4/1 10:24
     * @param order
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Order>>
     **/
    PageInfo<Order> findOrdersByExample(Order order, int page, int size);


    /**
     * @Description 统计设备订单
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 15:52
     * @param order
     * @return java.util.Map
     **/
    Map findOrdersCount(Order order);
}
