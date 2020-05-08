package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MerchantTransaction
 * @Description 商户流水统计实体类
 * @Author liuli
 * @Date 2020/4/1 11:33
 * @Version 1.0
 **/
@ApiModel(value = "MerchantTransaction",description = "商户流水统计实体类")
@Table(name = "zk_merchant_transaction")
public class MerchantTransaction implements Serializable {
    @ApiModelProperty(value = "商户id",required = true)
    @Id
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "时间",required = false)
    @Column(name = "time")
    private String time;
    @ApiModelProperty(value = "订单总金额",required = false)
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @ApiModelProperty(value = "退款总金额",required = false)
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;
    @ApiModelProperty(value = "实收总金额",required = false)
    @Column(name = "received_amount")
    private BigDecimal receivedAmount;
    @ApiModelProperty(value = "交易笔数",required = false)
    @Column(name = "transaction_number")
    private Integer transactionNumber;
    @ApiModelProperty(value = "退款笔数",required = false)
    @Column(name = "refund_number")
    private Integer refundNumber;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Integer getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Integer refundNumber) {
        this.refundNumber = refundNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
