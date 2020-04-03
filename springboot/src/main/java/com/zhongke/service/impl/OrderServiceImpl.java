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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
     * @Description 通过条件查询订单列表
     * @author liuli
     * @date 2020/4/1 10:24
     * @param order
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Order>>
     **/
    @Override
    public PageInfo<Order> findOrdersByExample(Order order, int page, int size) {
        if (order != null) {
            Integer deviceId = order.getDeviceId();
            String startTime = order.getStartTime();
            String endTime = order.getEndTime();
            String cashierName = order.getCashierName();
            if (deviceId != null){
                Order order1 = new Order();
                order1.setDeviceId(deviceId);
                List<Order> orders = orderMapper.select(order1);
                if (orders != null && orders.size()>0) {
                    for (int i = 0; i < orders.size(); i++) {
                        Order order2 = orders.get(i);
                        Device device = deviceMapper.selectByPrimaryKey(order.getDeviceId());
                        Store store = storeMapper.selectByPrimaryKey(device.getStoreId());
                        if (store != null) {
                            order2.setStoreName(store.getName());
                        }
                        if (cashierName != null) {
                            Cashier cashier1 = new Cashier();
                            cashier1.setName(cashierName);
                            List<Cashier> cashiers = cashierMapper.select(cashier1);
                            if (cashiers != null && cashiers.size()>0) {
                                if (order2.getCashierId() != cashiers.get(0).getId()){
                                    orders.remove(i);
                                    i--;
                                }
                            }else {
                                orders.remove(i);
                                i--;
                            }
                        }
                        Cashier cashier = cashierMapper.selectByPrimaryKey(order2.getCashierId());
                        if (cashier != null) {
                            order2.setCashierName(cashier.getName());
                        }
                        Date createTime = order2.getCreateTime();
                        if (createTime != null) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            if (startTime != null){
                                Date startTime1 = null;
                                try {
                                    startTime1 = format.parse(startTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (endTime != null){
                                    Date endTime1 = null;
                                    try {
                                        endTime1 = format.parse(endTime);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(endTime1);
                                    cal.add(Calendar.DATE,1);
                                    endTime1 = cal.getTime();
                                    if (1==createTime.compareTo(endTime1) || -1 == createTime.compareTo(startTime1)){
                                        orders.remove(i);
                                        i--;
                                    }
                                }else {
                                    if (createTime.compareTo(startTime1)== -1){
                                        orders.remove(i);
                                        i--;
                                    }
                                }
                            }
                        }
                    }
                }
                // 分页
                PageHelper.startPage(page,size);
                return new PageInfo<Order>(orders);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     * @Description 根据设备id查询订单列表
     * @author liuli
     * @date 2020/4/1 10:34
     * @param deviceId
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    @Override
    public List<Order> findOrdersByDeviceId(Integer deviceId) {
        Order order = new Order();
        order.setDeviceId(deviceId);
        return orderMapper.select(order);
    }
}
