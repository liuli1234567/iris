package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DestoryJam
 * @Description 会员等级实体类
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员等级")
@Table(name = "zk_member_grade")
public class MemberGrade implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "等级名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "等级图片",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "特权说明",required = false)
    @Column(name = "privilege")
    private String privilege;
    @ApiModelProperty(value = "充值金额",required = false)
    @Column(name = "recharge_money")
    private BigDecimal rechargeMoney;
    @ApiModelProperty(value = "消费折扣",required = false)
    @Column(name = "consum_discount")
    private double consumDiscount;
    @ApiModelProperty(value = "赠送金额",required = false)
    @Column(name = "give_money")
    private BigDecimal giveMoney;
    @ApiModelProperty(value = "赠送积分",required = false)
    @Column(name = "give_integral")
    private Integer giveIntegral;
    @ApiModelProperty(value = "商户id",required = false)
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public double getConsumDiscount() {
        return consumDiscount;
    }

    public void setConsumDiscount(double consumDiscount) {
        this.consumDiscount = consumDiscount;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}