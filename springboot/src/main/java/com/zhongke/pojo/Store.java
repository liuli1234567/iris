package com.zhongke.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Store
 * @Description 门店表
 * @Author liuli
 * @Date 2020/3/31 13:42
 * @Version 1.0
 **/
@Table(name = "zk_store")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 门店id
    @Column(name = "s_name")
    private String name; // 门店名称
    @Column(name = "s_address")
    private String address; // 门店地址
    @Column(name = "s_merchant_id")
    private Integer merchantId; // 门店所属商户id
    @Column(name = "createtime")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
