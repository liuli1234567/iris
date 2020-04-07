package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.DeviceMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Device;
import com.zhongke.pojo.Order;
import com.zhongke.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName DeviceServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/3/31 14:53
 * @Version 1.0
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired(required = false)
    private DeviceMapper deviceMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Device> devices(Device device,int page,int size) {
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(device);
        // 执行搜索
        List<Device> devices = deviceMapper.selectByExample(example);
        if (devices != null && devices.size()>0) {
            for (Device device1 : devices) {
                Order order = new Order();
                order.setDeviceId(device1.getId());
                order.setStatus(1);
                List<Order> orders = orderMapper.select(order);
                if (orders != null) {
                    device1.setSuccessNum(orders.size());
                }
                BigDecimal bigDecimal = new BigDecimal("0.0");
                if (orders != null && orders.size()>0) {
                    for (Order order1 : orders) {
                        bigDecimal = order1.getActuallyPaid().add(bigDecimal);
                    }
                    device1.setMoney(bigDecimal);
                }
            }
        }
        return new PageInfo<Device>(devices);
    }

    /**
     * @Description 构建搜索条件
     * @author liuli
     * @date 2020/4/2 17:27
     * @param device
     * @return tk.mybatis.mapper.entity.Example
     **/
    public Example createExample(Device device){
        Example example = new Example(Device.class);
        Example.Criteria criteria = example.createCriteria();
        if (device!=null){
            // 设备id
            if (!StringUtils.isEmpty(device.getId())){
                criteria.andEqualTo("id",device.getId());
            }
            // 设备序列号
            if (!StringUtils.isEmpty(device.getSerialNo())){
                criteria.andEqualTo("serialNumber",device.getSerialNo());
            }
            // 设备号
            if (!StringUtils.isEmpty(device.getDeviceNo())){
                criteria.andEqualTo("deviceNumber",device.getDeviceNo());
            }
            // 设备类型
            if (!StringUtils.isEmpty(device.getEquipmentType())){
                criteria.andEqualTo("equipmentType",device.getEquipmentType());
            }
            // 所属门店id
            if (!StringUtils.isEmpty(device.getStoreId())){
                criteria.andEqualTo("storeId",device.getStoreId());
            }
            // 所属门店名称
            if (!StringUtils.isEmpty(device.getStoreName())){
                criteria.andEqualTo("storeName",device.getStoreName());
            }
            // 收银模式（0 收银 1 收银+押金 2 收银+结算 3 收银+押金+结算）
            if (!StringUtils.isEmpty(device.getMode())){
                criteria.andEqualTo("mode",device.getMode());
            }
        }
        return example;
    }
}
