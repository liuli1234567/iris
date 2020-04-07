package com.zhongke.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Device
 * @Description 设备类
 * @Author liuli
 * @Date 2020/3/31 11:11
 * @Version 1.0
 **/
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
    @Column(name = "equipment_type")
    private String equipmentType; // 设备类型
    @Column(name = "store_id")
    private Integer storeId; // 所属门店id
    @Column(name = "d_store_name")
    private String storeName; // 所属门店名称
    @Column(name = "d_mode")
    private Integer mode; // 收银模式：0 收银 1 收银+押金 2 收银+结算 3 收银+押金+结算
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    private Integer successNum; // 交易成功笔数
    @Transient
    private BigDecimal money; // 交易金额

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

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
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
