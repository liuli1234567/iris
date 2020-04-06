package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.CashierMapper;
import com.zhongke.mapper.DeviceMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.mapper.StoreMapper;
import com.zhongke.pojo.Cashier;
import com.zhongke.pojo.Device;
import com.zhongke.pojo.Order;
import com.zhongke.pojo.Store;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/4/1 10:05
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private StoreMapper storeMapper;

    @Autowired(required = false)
    private DeviceMapper deviceMapper;

    @Autowired(required = false)
    private CashierMapper cashierMapper;

    /**
     * @Description 查询全部订单
     * @author liuli
     * @date 2020/4/1 10:13
     * @param
     * @return com.zhongke.entity.Result<com.zhongke.pojo.Order>
     **/
    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }

    /**
     * @Description 查询设备订单列表
     * @author liuli
     * @date 2020/4/1 10:24
     * @param order
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Order>>
     **/
    @Override
    public PageInfo<Order> findOrdersByExample(Order order, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(order.getDeviceId())){
            criteria.andEqualTo(order.getDeviceId());
        }
        if (!StringUtils.isEmpty(order.getOrderId())){
            criteria.andEqualTo(order.getOrderId());
        }
        if (!StringUtils.isEmpty(order.getStartTime())){
            if (!StringUtils.isEmpty(order.getEndTime())){
                criteria.andBetween("createTime",order.getStartTime()+" 00:00:00",order.getEndTime()+" 23:59:59");
            }else {
                criteria.andGreaterThanOrEqualTo("createTime",order.getStartTime()+" 00:00:00");
            }
        }else {
            if (!StringUtils.isEmpty(order.getEndTime())){
                criteria.andLessThanOrEqualTo("createTime",order.getEndTime()+" 23:59:59");
            }
        }
        List<Order> orders = orderMapper.selectByExample(example);
        return new PageInfo<>(orders);
    }

    @Override
    public Map findOrdersCount(Order order) {
        if (!StringUtils.isEmpty(order.getStartTime())){
            order.setStartTime(order.getStartTime()+" 00:00:00");
        }
        if (!StringUtils.isEmpty(order.getEndTime())){
            order.setStartTime(order.getEndTime()+" 23:59:59");
        }
        Integer orderNum = orderMapper.findOrderNum(order);
        Integer orderMoney = orderMapper.orderMoney(order);
        Integer refundMoney = orderMapper.refundMoney(order);
        Integer realMoney = orderMapper.realMoney(order);
        Integer discountMoney = orderMapper.discountMoney(order);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("orderNum",orderNum); // 订单总数
        map.put("orderMoney",orderMoney); // 订单总金额
        map.put("refundMoney",refundMoney); // 退款总金额
        map.put("realMoney",realMoney); // 顾客实付
        map.put("discountMoney",discountMoney); // 优惠
        return map;
    }
}
