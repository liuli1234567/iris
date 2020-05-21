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
    @ApiModelProperty(value = "0 未收款 1 已收款 2 已确认", required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "updatetime")
    private Date updatetime;
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "createtime")
    private Date createTime;

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
