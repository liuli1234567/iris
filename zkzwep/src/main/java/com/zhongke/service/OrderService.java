package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Order;

public interface OrderService {
    /**
     * @Description 查询取货码
     * @author liuli
     * @date 2020/5/21 10:03
     * @param orderNo
     * @return com.zhongke.pojo.Order
     **/
    Order findOrderCodeByOrderNo(String orderNo);

    /**
     * @Description 订单列表查询
     * @author liuli
     * @date 2020/5/21 16:43
     * @param nameOrPhone 客户姓名或手机号
     * @param status 订单状态
     * @param startTime 订单创建时间起始时间
     * @param endTime 订单创建时间结束时间
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Order>
     **/
    PageInfo<Order> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size);

    /**
     * @Description 财务修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return void
     **/
    void financeUpdate(int status, int id);

    /**
     * @Description 客服修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return void
     **/
    void customerUpdate(int status, int id);
}
