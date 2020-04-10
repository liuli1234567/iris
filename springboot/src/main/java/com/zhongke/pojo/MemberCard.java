package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MemberCard
 * @Description 会员卡
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@ApiModel(value = "MemberCard",description = "商户会员卡实体类")
@Table(name = "zk_member_card")
public class MemberCard implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "商户会员卡名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "logo url",required = false)
    @Column(name = "logo")
    private String logo;
    @ApiModelProperty(value = "背景图url",required = false)
    @Column(name = "bground_image")
    private String bgroundImage;
    @ApiModelProperty(value = "会员卡期限起始时间",required = false)
    @Column(name = "start_time")
    private Date startTime;
    @ApiModelProperty(value = "会员卡期限结束时间",required = false)
    @Column(name = "end_time")
    private Date endTime;
    @ApiModelProperty(value = "商户电话",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "会员特权说明",required = false)
    @Column(name = "privilege")
    private String privilege;
    @ApiModelProperty(value = "使用须知",required = false)
    @Column(name = "usage_notice")
    private String usageNotice;
    @ApiModelProperty(value = "赠送积分",required = false)
    @Column(name = "giftpoints")
    private Integer giftpoints;
    @ApiModelProperty(value = "赠送优惠卷id集合",required = false)
    @Column(name = "jam_ids")
    private String jamIds;
    @ApiModelProperty(value = "商户id",required = false)
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "商户名称",required = false)
    @Column(name = "merchant_name")
    private String merchantName;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBgroundImage() {
        return bgroundImage;
    }

    public void setBgroundImage(String bgroundImage) {
        this.bgroundImage = bgroundImage;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUsageNotice() {
        return usageNotice;
    }

    public void setUsageNotice(String usageNotice) {
        this.usageNotice = usageNotice;
    }

    public Integer getGiftpoints() {
        return giftpoints;
    }

    public void setGiftpoints(Integer giftpoints) {
        this.giftpoints = giftpoints;
    }

    public String getJamIds() {
        return jamIds;
    }

    public void setJamIds(String jamIds) {
        this.jamIds = jamIds;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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