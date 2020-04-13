package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Store
 * @Description 门店
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@ApiModel(value = "Store",description = "门店实体类")
@Table(name = "zk_store")
public class Store implements Serializable {
    @ApiModelProperty(value = "门店id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "门店编号",required = false)
    @Column(name = "store_no")
    private String storeNo;
    @ApiModelProperty(value = "门店名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "状态: 1 启用 2 禁用",required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "省份编号",required = false)
    @Column(name = "province_no")
    private Integer provinceNo;
    @ApiModelProperty(value = "省份名称",required = false)
    @Column(name = "province_name")
    private String provinceName;
    @ApiModelProperty(value = "城市编号",required = false)
    @Column(name = "city_no")
    private Integer cityNo;
    @ApiModelProperty(value = "城市名称",required = false)
    @Column(name = "city_name")
    private String cityName;
    @ApiModelProperty(value = "门店地址",required = false)
    @Column(name = "address")
    private String address;
    @ApiModelProperty(value = "地址备注",required = false)
    @Column(name = "address_remark")
    private String addressRemark;
    @ApiModelProperty(value = "门店电话",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "门店图片",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "门店logo",required = false)
    @Column(name = "logo")
    private String logo;
    @ApiModelProperty(value = "支付凭证描述",required = false)
    @Column(name = "payment_desciption")
    private String paymentDesciption;
    @ApiModelProperty(value = "行业类别",required = false)
    @Column(name = "industry_category")
    private String industryCategory;
    @ApiModelProperty(value = "门店所属商户id",required = false)
    @Column(name = "merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    @ApiModelProperty(value = "设备集合",required = false)
    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityNo() {
        return cityNo;
    }

    public void setCityNo(Integer cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressRemark() {
        return addressRemark;
    }

    public void setAddressRemark(String addressRemark) {
        this.addressRemark = addressRemark;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPaymentDesciption() {
        return paymentDesciption;
    }

    public void setPaymentDesciption(String paymentDesciption) {
        this.paymentDesciption = paymentDesciption;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
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