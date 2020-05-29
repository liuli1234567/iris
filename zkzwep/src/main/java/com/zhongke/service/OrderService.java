package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Order;

import java.util.List;

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

    /**
     * @Description 通过公众号用户openid查询订单列表
     * @author liuli
     * @date 2020/5/26 18:30
     * @param openid
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    List<Order> findByOpenid(String openid);

    /**
     * @Description 通过订单id查询订单信息
     * @author liuli
     * @date 2020/5/26 18:40
     * @param id
     * @return com.zhongke.pojo.Order
     **/
    Order findById(int id);

    /**
     * @Description 客服确认订单前提交合同资料
     * @author liuli
     * @date 2020/5/26 18:46
     * @param orderId 订单id
     * @param product_name 产品名称
     * @param product_number 产品吨数
     * @param person_name 联系人姓名
     * @param person_phone 联系人电话
     * @param address 自提地址
     * @return void
     **/
    void upload_data(int orderId, String product_name, int product_number, String person_name, String person_phone, String address);

    /**
     * @Description 通过订单id查询订单是否已出货
     * @author liuli
     * @date 2020/5/26 19:08
     * @param id
     * @return boolean
     **/
    int findOutById(int id);
}
