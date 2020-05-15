package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Device
 * @Description 设备类
 * @Author liuli
 * @Date 2020/3/31 11:11
 * @Version 1.0
 **/
@ApiModel(value = "Device",description = "设备")
@Table(name = "zk_device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 设备id
    @Column(name = "serial_no")
    private String serialNo; // 序列号
    @Column(name = "device_no")
    private String deviceNo; // 设备号
    @Column(name = "type")
    private String type; // 设备类型
    @Column(name = "store_name")
    private String storeName; // 所属门店名称
    @Column(name = "mode")
    private String mode; // 收银模式
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    private List<Order> orders; // 订单集合

    @Transient
    private Integer successNum; // 交易成功笔数
    @Transient
    private BigDecimal money; // 交易金额

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
