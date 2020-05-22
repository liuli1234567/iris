package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.entity.DateUtil;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Order;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:10
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    /**
     * @Description 查询取货码
     * @author liuli
     * @date 2020/5/21 10:04
     * @param orderNo
     * @return com.zhongke.pojo.Order
     **/
    @Override
    public Order findOrderCodeByOrderNo(String orderNo) {
        Order o = new Order();
        o.setOrderNo(orderNo);
        Order order = orderMapper.selectOne(o);
        if (order != null && order.getStatus() == 2){
            return order;
        }else {
            return null;
        }
    }

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
    @Override
    public PageInfo<Order> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size) {
        PageHelper.startPage(page,size);
        if (!StringUtils.isEmpty(startTime)){
            startTime = startTime+" 00:00:00";
        }
        if (!StringUtils.isEmpty(endTime)){
            endTime = endTime+"23:59:59";
        }
        List<Order> orders = orderMapper.findAll(nameOrPhone,status,startTime,endTime);
        return new PageInfo<>(orders);
    }

    /**
     * @Description 财务修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return void
     **/
    @Override
    public void financeUpdate(int status, int id) {
        if (-1 == status){
            // todo 向公众号发送财务驳回订单通知
        }
        if (1 == status){
            // todo 向公众号发送财务已确认收款通知
        }
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * @Description 客服修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return void
     **/
    @Override
    public void customerUpdate(int status, int id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setOrderNo(DateUtil.getTime());
        // todo 向公众号发送客服已确认订单通知
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
