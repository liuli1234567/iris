package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.*;
import com.zhongke.pojo.*;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
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
    private MemberMapper memberMapper;

    @Autowired(required = false)
    private MemberGradeMapper memberGradeMapper;

    @Autowired(required = false)
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired(required = false)
    private SpuMapper spuMapper;

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

    @Override
    public PageInfo<Order> findSpuOrders(String storeOrOrder, String startTime, String endTime, String payMethod, int page, int size) {
        PageHelper.startPage(page,size);
        List<Order> orders = orderMapper.findSpuOrders(storeOrOrder,startTime,endTime,payMethod);
        return new PageInfo<>(orders);
    }

    @Override
    public Map details(String orderId) {
        HashMap<String, Object> map = new HashMap<>();
        Order order = orderMapper.findOne(orderId);
        if (order != null) {
            map.put("storeName",order.getStoreName());// 商店名称
            map.put("orderId",order.getOrderId()); // 订单号
            map.put("payTime",order.getPayTime()); // 支付时间
            map.put("payMethod",order.getPayMethod()); // 支付方式
            map.put("orderAmount",order.getOrderAmount()); // 订单总金额
            map.put("discount",order.getDiscount()); // 优惠金额
            map.put("actuallyPaid",order.getActuallyPaid()); // 实付金额
            Member member = memberMapper.selectByPrimaryKey(order.getMemberId());
            if (member != null) {
                map.put("image",member.getImage()); // 会员头像
                map.put("nickName",member.getNickName()); // 会员昵称
                MemberGrade memberGrade = memberGradeMapper.selectByPrimaryKey(member.getGradeId());
                if (memberGrade != null) {
                    map.put("memberGradeName",memberGrade.getName()); // 会员等级名称
                }
            }
        }
        List<OrderDetails> orderDetails = orderDetailsMapper.orderDetails(orderId);
        ArrayList<Spu> spus = new ArrayList<>();
        if (orderDetails != null && orderDetails.size()>0) {
            for (OrderDetails orderDetail : orderDetails) {
                Spu spu = spuMapper.selectBySpuId(orderDetail.getSpuId());
                if (spu != null) {
                    spu.setSpuSum(spu.getPrice().multiply(new BigDecimal(orderDetail.getSpuNum())));
                }
                spus.add(spu);
            }
        }
        map.put("spus",spus);
        return map;
    }
}
