package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Order
 * @Description 订单实体类
 * @Author liuli
 * @Date 2020/3/31 16:57
 * @Version 1.0
 **/
@ApiModel(value = "Order",description = "订单")
@Table(name = "zk_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_id")
    private String orderId; // 订单号
    @Column(name = "order_amount")
    private BigDecimal orderAmount; // 订单金额
    @Column(name = "buyer_logon_id")
    private String buyerLogonId; // 买家支付宝账号
    @Column(name = "buyer_user_id")
    private String buyerUserId; // 买家userID
    @Column(name = "status")
    private Integer status; // 订单状态: 0 未支付 1 已支付 -1 已取消 -3 支付失败
    @Column(name = "code")
    private String code; // 支付状态码
    @Column(name = "msg")
    private String msg; // 支付描述
    @Column(name = "sub_code")
    private String subCode; // 支付错误码
    @Column(name = "sub_msg")
    private String subMsg; // 支付错误描述
    @Column(name = "pay_method")
    private String payMethod; // 支付方式
    @Column(name = "pay_aisle")
    private String payAisle; // 支付通道
    @Column(name = "actually_paid")
    private BigDecimal actuallyPaid; // 实付金额
    @Column(name = "discount")
    private BigDecimal discount; // 优惠金额
    @Column(name = "transaction_id")
    private String transactionId; // 交易流水号
    @Column(name = "spu_ids")
    private String spuIds; // 商品id集合
    @Column(name = "jam_id")
    private Integer jamId; // 优惠卷id
    @Column(name = "full_rule_id")
    private Integer fullRuleId; // 满减规则id
    @Column(name = "fund_channel")
    private String fundChannel; // 支付渠道
    @Column(name = "device_id")
    private Integer deviceId; // 设备id
    @Column(name = "merchant_id")
    private Integer merchantId; // 商户id
    @Column(name = "refund_buyer_amount")
    private BigDecimal refundBuyerAmount; // 买家退款金额
    @Column(name = "refund_discount_amount")
    private BigDecimal refundDiscountAmount; // 平台优惠退款金额
    @Column(name = "refund_mdiscount_amount")
    private BigDecimal refundMdiscountAmount; // 商家优惠退款金额
    @Column(name = "member_id")
    private Integer memberId; // 会员id
    @Column(name = "member_name")
    private Integer memberName; // 会员名字
    @Column(name = "cashier_id")
    private Integer cashierId; // 收银员id
    @Column(name = "cashier_name")
    private Integer cashierName; // 收银员名字
    @Column(name = "store_name")
    private String storeName; // 门店名称
    @Column(name = "order_count")
    private Integer orderCount; // 设备订单总数
    @Column(name = "total_money")
    private BigDecimal totalMoney; // 设备订单总金额
    @Column(name = "refund_total")
    private BigDecimal refundTotal; // 设备退款总金额
    @Column(name = "payment_total")
    private BigDecimal paymentTotal; // 设备顾客实付总金额
    @Column(name = "discount_total")
    private BigDecimal discountTotal; // 设备优惠总金额
    @Column(name = "updatetime")
    private Date updatetime; // 更新时间
    @Column(name = "createtime")
    private Date createTime; // 创建时间
    @Column(name = "pay_time")
    private Date payTime; // 支付时间

    @Transient
    private List<Spu> spus; // 商品集合
    @Transient
    private Integer storeId; // 门店id
    @Transient
    private String tableNewName; // 订单新表名

    @Transient
    private String startTime; // 起始时间
    @Transient
    private String endTime; // 结束时间

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(BigDecimal refundTotal) {
        this.refundTotal = refundTotal;
    }

    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getTableNewName() {
        return tableNewName;
    }

    public void setTableNewName(String tableNewName) {
        this.tableNewName = tableNewName;
    }

    public List<Spu> getSpus() {
        return spus;
    }

    public void setSpus(List<Spu> spus) {
        this.spus = spus;
    }

    public BigDecimal getRefundBuyerAmount() {
        return refundBuyerAmount;
    }

    public void setRefundBuyerAmount(BigDecimal refundBuyerAmount) {
        this.refundBuyerAmount = refundBuyerAmount;
    }

    public BigDecimal getRefundDiscountAmount() {
        return refundDiscountAmount;
    }

    public void setRefundDiscountAmount(BigDecimal refundDiscountAmount) {
        this.refundDiscountAmount = refundDiscountAmount;
    }

    public BigDecimal getRefundMdiscountAmount() {
        return refundMdiscountAmount;
    }

    public void setRefundMdiscountAmount(BigDecimal refundMdiscountAmount) {
        this.refundMdiscountAmount = refundMdiscountAmount;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(String fundChannel) {
        this.fundChannel = fundChannel;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMemberName() {
        return memberName;
    }

    public void setMemberName(Integer memberName) {
        this.memberName = memberName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSpuIds() {
        return spuIds;
    }

    public void setSpuIds(String spuIds) {
        this.spuIds = spuIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayAisle() {
        return payAisle;
    }

    public void setPayAisle(String payAisle) {
        this.payAisle = payAisle;
    }

    public BigDecimal getActuallyPaid() {
        return actuallyPaid;
    }

    public void setActuallyPaid(BigDecimal actuallyPaid) {
        this.actuallyPaid = actuallyPaid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getJamId() {
        return jamId;
    }

    public void setJamId(Integer jamId) {
        this.jamId = jamId;
    }

    public Integer getFullRuleId() {
        return fullRuleId;
    }

    public void setFullRuleId(Integer fullRuleId) {
        this.fullRuleId = fullRuleId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public Integer getCashierName() {
        return cashierName;
    }

    public void setCashierName(Integer cashierName) {
        this.cashierName = cashierName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
