package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName Merchant
 * @Description 商户实体类
 * @Author liuli
 * @Date 2020/4/1 16:59
 * @Version 1.0
 **/
@ApiModel(value = "Merchant",description = "商户")
@Table(name = "zk_merchant")
public class Merchant implements Serializable {

    @Id
    @ApiModelProperty(value = "商户id",required = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 商户id
    @ApiModelProperty(value = "商户名称",required = false)
    @Column(name = "name")
    private String name; // 商户名称
    @ApiModelProperty(value = "商户号",required = false)
    @Column(name = "number")
    private String number; // 商户号
    @ApiModelProperty(value = "商户收款人姓名",required = false)
    @Column(name = "payee_name")
    private String payeeName; // 商户收款人姓名
    @ApiModelProperty(value = "商户收款人银行卡号",required = false)
    @Column(name = "payee_bank")
    private String payeeBank; // 商户收款人银行卡号
    @ApiModelProperty(value = "开户行",required = false)
    @Column(name = "bank_name")
    private String bankName; // 开户行
    @ApiModelProperty(value = "商户地址",required = false)
    @Column(name = "address")
    private String address; // 商户地址
    @ApiModelProperty(value = "商户电话",required = false)
    @Column(name = "tel")
    private String tel; // 商户电话
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    @ApiModelProperty(value = "门店集合",required = false)
    private List<Store> stores;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
