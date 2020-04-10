package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AlipayMemberCard
 * @Description 支付宝会员卡
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@ApiModel(value = "AlipayMemberCard",description = "商户会员卡实体类")
@Table(name = "zk_alipay_member_card")
public class AlipayMemberCard implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "支付宝会员卡名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "logo url",required = false)
    @Column(name = "logo")
    private String logo;
    @ApiModelProperty(value = "图片url",required = false)
    @Column(name = "image")
    private String image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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