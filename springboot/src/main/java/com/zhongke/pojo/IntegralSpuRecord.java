package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @author liuli
 * @date 2020/4/6 22:03
 * @param
 * @return
 **/
@ApiModel(value = "IntegralSpuRecord",description = "积分商品兑换记录实体类")
@Table(name = "zk_integral_spu_record")
public class IntegralSpuRecord implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "商品id",required = false)
    @Column(name = "spu_id")
    private Integer spuId;
    @ApiModelProperty(value = "商品名称",required = false)
    @Column(name = "spu_name")
    private String spuName;
    @ApiModelProperty(value = "消耗积分",required = false)
    @Column(name = "consum_point")
    private Integer consumPoint;
    @ApiModelProperty(value = "提货码",required = false)
    @Column(name = "code")
    private String code;
    @ApiModelProperty(value = "兑换人手机号",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "可用积分",required = false)
    @Column(name = "available_point")
    private Integer availablePoint;
    @ApiModelProperty(value = "使用状态",required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "操作人",required = false)
    @Column(name = "operator")
    private String operator;
    @ApiModelProperty(value = "兑换时间",required = false)
    @Column(name = "exchange_time")
    private Date exchangeTime;

    @Transient
    private String startTime;
    @Transient
    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public Integer getConsumPoint() {
        return consumPoint;
    }

    public void setConsumPoint(Integer consumPoint) {
        this.consumPoint = consumPoint;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAvailablePoint() {
        return availablePoint;
    }

    public void setAvailablePoint(Integer availablePoint) {
        this.availablePoint = availablePoint;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}