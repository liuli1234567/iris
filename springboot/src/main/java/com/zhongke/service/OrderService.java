package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Order;

import java.util.List;

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
     * @Description 通过条件查询订单列表并分页
     * @author liuli
     * @date 2020/4/1 10:24
     * @param order
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Order>>
     **/
    PageInfo<Order> findOrdersByExample(Order order, int page, int size);

    /**
     * @Description 根据设备id查询订单列表
     * @author liuli
     * @date 2020/4/1 10:34
     * @param deviceId
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    List<Order> findOrdersByDeviceId(Integer deviceId);

}
