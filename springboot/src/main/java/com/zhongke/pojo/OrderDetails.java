package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName OrderDetails
 * @Description 订单详情
 * @Author liuli
 * @Date 2020/4/3 18:12
 * @Version 1.0
 **/
@ApiModel(value = "OrderDetails",description = "订单详情实体类")
@Table(name = "zk_order_details")
public class OrderDetails implements Serializable {

    @ApiModelProperty(value = "订单号",required = false)
    @Id
    @Column(name = "order_id")
    private String orderId;
    @ApiModelProperty(value = "商品id",required = false)
    @Column(name = "spu_id")
    private Integer spuId;
    @ApiModelProperty(value = "商品数量",required = false)
    @Column(name = "spu_num")
    private Integer spuNum;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getSpuNum() {
        return spuNum;
    }

    public void setSpuNum(Integer spuNum) {
        this.spuNum = spuNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
