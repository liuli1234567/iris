package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zhongke.entity.DateUtil;
import com.zhongke.mapper.*;
import com.zhongke.pojo.*;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
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

    @Autowired(required = false)
    private PlatformUserMapper platformUserMapper;

    @Autowired(required = false)
    private StoreMapper storeMapper;

    @Autowired(required = false)
    private DeviceMapper deviceMapper;

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

    @Override
    public Map merchant_transactionOverview(String payStartTime, String payEndTime) {
        // 获取当前登录用户的账户名
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            PlatformUser platformUser = new PlatformUser();
            platformUser.setName(user.getUsername());
            PlatformUser platformUser1 = platformUserMapper.selectOne(platformUser);
            if (platformUser1 != null) {
                List<Store> stores = storeMapper.findByMerchantId(platformUser1.getMerchantId());
                if (stores != null && stores.size() > 0) {
                    BigDecimal merchantPaidMoney = new BigDecimal("0.0");
                    BigDecimal retreatMoney = new BigDecimal("0.0");
                    BigDecimal actuallyPaidMoney = new BigDecimal("0.0");
                    Integer orderTotal = 0;
                    BigDecimal orderTotalMoney = new BigDecimal("0.0");
                    BigDecimal merchantDiscount = new BigDecimal("0.0");
                    Integer refundOrderCount = 0;
                    BigDecimal refundOrderMoney = new BigDecimal("0.0");
                    for (Store store : stores) {
                        List<Device> devices = deviceMapper.findDevicesByStoreId(store.getId());
                        if (devices != null && devices.size() > 0) {
                            for (Device device : devices) {
                                Integer deviceId = device.getId();
                                merchantPaidMoney = merchantPaidMoney.add(orderMapper.merchantPaidMoney(payStartTime, payEndTime,deviceId)); // 商户实收总金额
                                retreatMoney = retreatMoney.add(orderMapper.retreatMoney(payStartTime, payEndTime,deviceId)); // 商户实退总金额
                                actuallyPaidMoney = actuallyPaidMoney.add(orderMapper.actuallyPaidMoney(payStartTime, payEndTime,deviceId)); // 顾客实付金额
                                orderTotal += orderMapper.orderTotal(payStartTime, payEndTime,deviceId); // 支付成功订单总数
                                orderTotalMoney = orderTotalMoney.add(orderMapper.orderTotalMoney(payStartTime, payEndTime,deviceId)); // 支付成功订单总金额
                                merchantDiscount = merchantDiscount.add(orderMapper.merchantDiscount(payStartTime, payEndTime,deviceId)); // 商家优惠总金额
                                //BigDecimal otherDiscount = orderMapper.otherDiscount(payStartTime, payEndTime,deviceId); // 其他方优惠总金额
                                refundOrderCount += orderMapper.refundOrderCount(payStartTime, payEndTime,deviceId); // 退款订单总数
                                refundOrderMoney = refundOrderMoney.add(orderMapper.refundOrderMoney(payStartTime, payEndTime,deviceId)); // 退款订单总金额
                            }
                        }
                    }
                    map.put("merchantPaidMoney", merchantPaidMoney.doubleValue());
                    map.put("retreatMoney", retreatMoney.doubleValue());
                    map.put("actualRevenue", merchantPaidMoney.subtract(retreatMoney).doubleValue()); // 商户实际营收（实收-实退）
                    map.put("actuallyPaidMoney", actuallyPaidMoney.doubleValue());
                    map.put("orderTotal", orderTotal);
                    map.put("orderTotalMoney", orderTotalMoney.doubleValue());
                    map.put("merchantDiscount", merchantDiscount.doubleValue());
                    map.put("otherDiscount", 0.0);
                    map.put("refundOrderCount", refundOrderCount);
                    map.put("refundOrderMoney", refundOrderMoney.doubleValue());
                    return map;
                }
            }
        }
        return null;
    }

    @Override
    public PageInfo<List<Map<String,Object>>> store_transactionOverview(String payStartTime, String payEndTime, int page, int size) {
        PageHelper.startPage(page,size);
        // 获取当前登录用户的账户名
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            PlatformUser platformUser = new PlatformUser();
            platformUser.setName(user.getUsername());
            PlatformUser platformUser1 = platformUserMapper.selectOne(platformUser);
            if (platformUser1 != null) {
                List<Store> stores = storeMapper.findByMerchantId(platformUser1.getMerchantId());
                if (stores != null && stores.size() > 0) {
                    List<Map<String,Object>> list = new ArrayList<>();
                    for (Store store : stores) {
                        Integer effectiveOrderNum = 0;
                        BigDecimal effectiveOrderMoney = new BigDecimal("0.0");
                        BigDecimal retreatMoney = new BigDecimal("0.0");
                        BigDecimal actuallyPaidMoney = new BigDecimal("0.0");
                        BigDecimal discountMoney = new BigDecimal("0.0");
                        Map<String, Object> hashMap = new HashMap<>();
                        List<Device> devices = deviceMapper.findDevicesByStoreId(store.getId());
                        if (devices != null && devices.size() > 0) {
                            for (Device device : devices) {
                                Integer deviceId = device.getId();
                                effectiveOrderNum += orderMapper.effectiveOrderNum(payStartTime, payEndTime, deviceId); // 有效订单数
                                effectiveOrderMoney = effectiveOrderMoney.add(orderMapper.effectiveOrderMoney(payStartTime, payEndTime, deviceId)); // 有效订单金额
                                retreatMoney = retreatMoney.add(orderMapper.retreatMoney(payStartTime, payEndTime, deviceId)); // 退款总金额
                                actuallyPaidMoney = actuallyPaidMoney.add(orderMapper.actuallyPaidMoney(payStartTime, payEndTime, deviceId)); // 顾客实付金额
                                discountMoney = discountMoney.add(orderMapper.merchantDiscount(payStartTime, payEndTime, deviceId)); // 优惠金额
                            }
                        }
                        hashMap.put("storeName",store.getName()); // 门店名称
                        hashMap.put("storeId",store.getName()); // 门店id
                        hashMap.put("effectiveOrderNum",effectiveOrderNum);
                        hashMap.put("effectiveOrderMoney",effectiveOrderMoney.doubleValue());
                        hashMap.put("retreatMoney",retreatMoney.doubleValue());
                        hashMap.put("actuallyPaidMoney",actuallyPaidMoney.doubleValue());
                        hashMap.put("discountMoney",discountMoney.doubleValue());
                        list.add(hashMap);
                    }
                    return new PageInfo<>(Collections.singletonList(list));
                }
            }
                }
        return new PageInfo<>();
    }

    @Override
    public PageInfo<Order> findOrdersByStoreId(Order order, int page, int size) {
        List<Order> orders = orderMapper.findOrdersByStoreId(order);
        PageHelper.startPage(page,size);
        return new PageInfo<>(orders);
    }

    @Override
    public void add(Order order) {
        orderMapper.insertSelective(order);
    }

    /**
     * @Description 更新订单状态
     * @author liuli
     * @date 2020/4/17 15:33
     * @param orderId 订单号
     * @param transaction_id 微信生成的交易流水号
     * @param pay_end_time 支付完成时间
     * @param status 支付状态
     * @return void
     **/
    @Override
    public void updateStatus(String orderId, String transaction_id, String pay_end_time, int status) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        // 更新订单状态
        order.setTransactionId(transaction_id);
        order.setUpdatetime(new Date());        // 订单更新时间
        order.setPayTime(DateUtil.StringToDate(pay_end_time,"yyyyMMddHHmmss")); // 支付完成时间
        order.setStatus(status);  // 订单状态（支付状态）
    }
}
