package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName FullRule
 * @Description 满减规则
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@Api(value = "满减规则实体类")
@Table(name = "zk_full_rule")
public class FullRule implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "规则名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "开始时间",required = false)
    @Column(name = "start_time")
    private Date startTime;
    @ApiModelProperty(value = "结束时间",required = false)
    @Column(name = "end_time")
    private Date endTime;
    @ApiModelProperty(value = "满金额",required = false)
    @Column(name = "full_money")
    private BigDecimal fullMoney;
    @ApiModelProperty(value = "减金额",required = false)
    @Column(name = "less_money")
    private BigDecimal lessMoney;
    @ApiModelProperty(value = "状态: 1 启用 2 禁用",required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "商户id",required = false)
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @ApiModelProperty(value = "活动状态",required = false)
    @Transient
    private String activityStatus;

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    public BigDecimal getLessMoney() {
        return lessMoney;
    }

    public void setLessMoney(BigDecimal lessMoney) {
        this.lessMoney = lessMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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