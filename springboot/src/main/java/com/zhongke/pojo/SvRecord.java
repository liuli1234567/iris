package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SvRecord
 * @Description 储值记录实体类
 * @Author liuli
 * @Date 2020/4/3 15:33
 * @Version 1.0
 **/
@ApiModel(value = "SvRecord",description = "会员储值记录实体类")
@Table(name = "zk_sv_record")
public class SvRecord implements Serializable {

    @ApiModelProperty(value = "储值记录id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "订单号",required = false)
    @Column(name = "orderNumber")
    private String orderNumber;
    @ApiModelProperty(value = "手机号",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "交易类型:1 充值 2 消费 -1 退款 3 导入 4 会员升级赠送",required = false)
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(value = "交易金额",required = false)
    @Column(name = "money")
    private BigDecimal money;
    @ApiModelProperty(value = "赠送金额",required = false)
    @Column(name = "give_money")
    private BigDecimal giveMoney;
    @ApiModelProperty(value = "交易后余额",required = false)
    @Column(name = "balance")
    private BigDecimal balance;
    @ApiModelProperty(value = "操作人",required = false)
    @Column(name = "operator")
    private String operator;
    @ApiModelProperty(value = "来源：1 PC端 2 APP端 -1 其他 3 POS机",required = false)
    @Column(name = "source")
    private Integer source;
    @ApiModelProperty(value = "支付方式: 1 微信 2 支付宝 3 储值卡 4 H5 5 导入",required = false)
    @Column(name = "pay_way")
    private Integer payWay;
    @ApiModelProperty(value = "门店名称",required = false)
    @Column(name = "store_name")
    private String storeName;
    @ApiModelProperty(value = "会员id",required = false)
    @Column(name = "member_id")
    private Integer memberId;
    @ApiModelProperty(value = "赠送积分",required = false)
    @Column(name = "give_gral")
    private Integer giveGral;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getGiveGral() {
        return giveGral;
    }

    public void setGiveGral(Integer giveGral) {
        this.giveGral = giveGral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
