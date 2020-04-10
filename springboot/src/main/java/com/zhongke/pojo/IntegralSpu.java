package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 积分商品
 * @author liuli
 * @date 2020/4/6 18:36
 * @param
 * @return
 **/
@ApiModel(value = "IntegralSpu",description = "积分商品实体类")
@Table(name = "zk_integral_spu")
public class IntegralSpu implements Serializable {
    @ApiModelProperty(value = "积分商品id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "积分商品名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "商品价格",required = false)
    @Column(name = "price")
    private BigDecimal price;
    @ApiModelProperty(value = "商品图片url",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "商品数量",required = false)
    @Column(name = "num")
    private Integer num;
    @ApiModelProperty(value = "已兑换商品数量",required = false)
    @Column(name = "redeemed_num")
    private Integer redeemedNum;
    @ApiModelProperty(value = "所需积分",required = false)
    @Column(name = "integral_num")
    private Integer integralNum;
    @ApiModelProperty(value = "每位用户限兑",required = false)
    @Column(name = "user_num")
    private Integer userNum;
    @ApiModelProperty(value = "兑换开始时间",required = false)
    @Column(name = "start_time")
    private Date startTime;
    @ApiModelProperty(value = "兑换结束时间",required = false)
    @Column(name = "end_time")
    private Date endTime;
    @ApiModelProperty(value = "兑换说明",required = false)
    @Column(name = "description")
    private String description;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @ApiModelProperty(value = "活动状态：1 进行中 2 未开始 -1 已结束",required = false)
    @Transient
    private Integer status;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRedeemedNum() {
        return redeemedNum;
    }

    public void setRedeemedNum(Integer redeemedNum) {
        this.redeemedNum = redeemedNum;
    }

    public Integer getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}