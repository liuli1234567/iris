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

    /**
     * @Description 查询商品订单列表
     * @author liuli
     * @date 2020/4/7 16:04
     * @param storeOrOrder 门店名称或订单号
     * @param startTime
     * @param endTime
     * @param payMethod 支付方式
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Order>
     **/
    PageInfo<Order> findSpuOrders(String storeOrOrder, String startTime, String endTime, String payMethod, int page, int size);

    /**
     * @Description 查询订单详情
     * @author liuli
     * @date 2020/4/7 17:46
     * @param orderId
     * @return java.util.Map
     **/
    Map details(String orderId);

    /**
     * @Description 查询商户流水概览
     * @author liuli
     * @date 2020/4/13 14:36
     * @param payStartTime
     * @param payEndTime
     * @return java.util.Map
     **/
    Map merchant_transactionOverview(String payStartTime, String payEndTime);

    /**
     * @Description 查询门店流水概览
     * @author liuli
     * @date 2020/4/14 13:47
     * @param payStartTime
     * @param payEndTime
     * @param page
     * @param size
     * @return java.util.Map
     **/
    PageInfo<List<Map<String,Object>>> store_transactionOverview(String storeName,String payStartTime, String payEndTime, int page, int size);

    /**
     * @Description 根据门店id和订单信息查询订单列表
     * @author liuli
     * @date 2020/4/14 16:49
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Order>
     **/
    PageInfo<Order> findOrdersByStoreId(Order order,int page, int size);

    /**
     * @Description 新增订单
     * @author liuli
     * @date 2020/4/17 10:51
     * @param order
     * @return void
     **/
    void add(Order order);

    /**
     * @Description 更新订单状态
     * @author liuli
     * @date 2020/4/17 15:33
     * @param out_trade_no 订单号
     * @param transaction_id 微信生成的交易流水号
     * @param pay_end_time 支付完成时间
     * @param status 支付状态
     * @return void
     **/
    void updateStatus(String out_trade_no, String transaction_id, String pay_end_time, int status);
}
