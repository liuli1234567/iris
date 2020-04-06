package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName IntegralRecord
 * @Description 积分记录表
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "核销卡卷实体类")
public class IntegralRecord implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "类型：1 积分商品兑换 2 充值赠送 3 消费 4 退款 5 开卡赠送 6 消费抵扣",required = false)
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(value = "交易积分",required = false)
    @Column(name = "transaction_num")
    private Integer transactionNum;
    @ApiModelProperty(value = "积分余额",required = false)
    @Column(name = "num")
    private Integer num;
    @ApiModelProperty(value = "会员id",required = false)
    @Column(name = "member_id")
    private Integer memberId;
    @ApiModelProperty(value = "操作人",required = false)
    @Column(name = "operator")
    private String operator;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    private String startTime;
    @Transient
    private String endTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(Integer transactionNum) {
        this.transactionNum = transactionNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}