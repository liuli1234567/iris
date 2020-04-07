package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MembpaySetting
 * @Description 会员支付设置
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@Api(value = "会员支付设置实体类")
@Table(name = "zk_membpay_setting")
public class MembpaySetting implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "充值赠送的金额：2 下次消费才可用 1 可立即使用",required = false)
    @Column(name = "is_use")
    private Integer isUse;
    @ApiModelProperty(value = "积分是否可抵扣金额：1 可以 2 不可以",required = false)
    @Column(name = "is_deduct")
    private Integer isDeduct;
    @ApiModelProperty(value = "满积分",required = false)
    @Column(name = "full_point")
    private Integer fullPoint;
    @ApiModelProperty(value = "抵扣金额",required = false)
    @Column(name = "deduct_money")
    private BigDecimal deductMoney;
    @ApiModelProperty(value = "最多抵扣积分数目",required = false)
    @Column(name = "max_deduct")
    private Integer maxDeduct;
    @ApiModelProperty(value = "展示满减：1 展示 2 不展示",required = false)
    @Column(name = "is_full_less")
    private Integer isFullLess;
    @ApiModelProperty(value = "展示优惠：1 展示 2 不展示",required = false)
    @Column(name = "is_discount")
    private Integer isDiscount;
    @ApiModelProperty(value = "商户id",required = false)
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsDeduct() {
        return isDeduct;
    }

    public void setIsDeduct(Integer isDeduct) {
        this.isDeduct = isDeduct;
    }

    public Integer getFullPoint() {
        return fullPoint;
    }

    public void setFullPoint(Integer fullPoint) {
        this.fullPoint = fullPoint;
    }

    public BigDecimal getDeductMoney() {
        return deductMoney;
    }

    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }

    public Integer getMaxDeduct() {
        return maxDeduct;
    }

    public void setMaxDeduct(Integer maxDeduct) {
        this.maxDeduct = maxDeduct;
    }

    public Integer getIsFullLess() {
        return isFullLess;
    }

    public void setIsFullLess(Integer isFullLess) {
        this.isFullLess = isFullLess;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}