package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.SendMessage;
import com.zhongke.mapper.AccessTokenMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.AccessToken;
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
    @Autowired(required = false)
    private AccessTokenMapper accessTokenMapper;

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
        // 从数据库获取access_token
        AccessToken token = new AccessToken();
        token.setId(1);
        AccessToken accessToken = accessTokenMapper.selectByPrimaryKey(token);

        Order o = new Order();
        o.setId(id);
        if (-1 == status){
            // todo 向公众号发送财务驳回订单通知
            Order order = orderMapper.selectByPrimaryKey(o);
            String openid = o.getOpenid();
            SendMessage.sendOrderFalseMessage(accessToken.getAccessToken(), openid, "");
        }
        if (1 == status){
            // todo 向公众号发送财务已确认收款通知
        }
        o.setStatus(status);
        orderMapper.updateByPrimaryKeySelective(o);
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
        // 从数据库获取access_token
        AccessToken token = new AccessToken();
        token.setId(1);
        AccessToken accessToken = accessTokenMapper.selectByPrimaryKey(token);

        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setOrderNo(DateUtil.getTime());
        // todo 向公众号发送客服已确认订单通知
        Order o = orderMapper.selectByPrimaryKey(order);
        String openid = o.getOpenid();
        SendMessage.sendOrderTrueMessage(accessToken.getAccessToken(), openid, "http://111.230.205.101/#/remittanceSuccess?id="+o.getId());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * @Description 通过公众号用户openid查询订单列表
     * @author liuli
     * @date 2020/5/26 18:30
     * @param openid
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    @Override
    public List<Order> findByOpenid(String openid) {
        return orderMapper.findByOpenid(openid);
    }

    /**
     * @Description 通过订单id查询订单信息
     * @author liuli
     * @date 2020/5/26 18:40
     * @param id
     * @return com.zhongke.pojo.Order
     **/
    @Override
    public Order findById(int id) {
        Order order = new Order();
        order.setId(id);
        return orderMapper.selectByPrimaryKey(order);
    }

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
    @Override
    public void upload_data(int orderId, String product_name, int product_number, String person_name, String person_phone, String address) {
        Order order = new Order();
        order.setId(orderId);
        order.setProductName(product_name);
        order.setProductNumber(product_number);
        order.setPersonName(person_name);
        order.setPersonPhone(person_phone);
        order.setAddress(address);
    }

    /**
     * @Description 通过订单id查询订单是否已出货
     * @author liuli
     * @date 2020/5/26 19:08
     * @param id
     * @return boolean
     **/
    @Override
    public int findOutById(int id) {
        Order o = new Order();
        o.setId(id);
        Order order = orderMapper.selectByPrimaryKey(o);
        if (order != null) {
            if (0 == order.getIsOut()){
                o.setStatus(1);
                orderMapper.updateByPrimaryKeySelective(o);
                return 0;
            }
            if (1 == order.getIsOut()){
                return 1;
            }else {
                return -1;
            }
        }else {
            return -1;
        }
    }
}
