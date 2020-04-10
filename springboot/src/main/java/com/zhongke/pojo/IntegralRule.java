package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 积分规则
 * @author liuli
 * @date 2020/4/6 18:36
 * @param
 * @return
 **/
@ApiModel(value = "IntegralRule",description = "积分规则实体类")
@Table(name = "zk_integral_rule")
public class IntegralRule implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "规则名称",required = false)
    @Column(name = "name")
    private String type;
    @ApiModelProperty(value = "消费金额",required = false)
    @Column(name = "consum_money")
    private BigDecimal consumMoney;
    @ApiModelProperty(value = "赠送积分",required = false)
    @Column(name = "give_integral")
    private Integer giveIntegral;
    @ApiModelProperty(value = "储值消费是否积分：1 是 2 否",required = false)
    @Column(name = "is_integral")
    private Integer isIntegral;
    @ApiModelProperty(value = "规则所属商户id",required = false)
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getConsumMoney() {
        return consumMoney;
    }

    public void setConsumMoney(BigDecimal consumMoney) {
        this.consumMoney = consumMoney;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Integer getIsIntegral() {
        return isIntegral;
    }

    public void setIsIntegral(Integer isIntegral) {
        this.isIntegral = isIntegral;
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