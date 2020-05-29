package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单表
 * @Author liuli
 * @Date 2020/5/20 15:38
 * @Version 1.0
 **/
@ApiModel(value = "Order",description = "订单表实体类")
@Table(name = "tb_order")
public class Order implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "客户openid", required = false)
    @Column(name = "openid")
    private String openid;
    @ApiModelProperty(value = "客户手机号", required = false)
    @Column(name = "phone")
    private String phone;
    @ApiModelProperty(value = "客户姓名", required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "订单号", required = false)
    @Column(name = "order_no")
    private String orderNo;
    @ApiModelProperty(value = "0 未收款 1 已收款 -1 驳回 2 已确认", required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "0 未出货 1 已出货", required = false)
    @Column(name = "is_out")
    private Integer isOut;
    @ApiModelProperty(value = "合同编号", required = false)
    @Column(name = "contract_no")
    private String contractNo;
    @ApiModelProperty(value = "产品名称", required = false)
    @Column(name = "product_name")
    private String productName;
    @ApiModelProperty(value = "产品吨数", required = false)
    @Column(name = "product_number")
    private Integer productNumber;
    @ApiModelProperty(value = "联系人姓名", required = false)
    @Column(name = "person_name")
    private String personName;
    @ApiModelProperty(value = "联系人电话", required = false)
    @Column(name = "person_phone")
    private String personPhone;
    @ApiModelProperty(value = "自提地址", required = false)
    @Column(name = "address")
    private String address;
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "updatetime")
    private Date updatetime;
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "createtime")
    private Date createTime;

    public String getContractNo() {
        return contractNo;
    }

    public Integer getIsOut() {
        return isOut;
    }

    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
