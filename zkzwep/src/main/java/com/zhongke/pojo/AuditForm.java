package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AuditForm
 * @Description 审核表
 * @Author liuli
 * @Date 2020/5/20 15:18
 * @Version 1.0
 **/
@ApiModel(value = "AuditForm",description = "客户资料审核表实体类")
@Table(name = "tb_audit_form")
public class AuditForm implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "客户openid", required = false)
    @Column(name = "client_openid")
    private String clientOpenid;
    @ApiModelProperty(value = "客户手机号", required = false)
    @Column(name = "client_phone")
    private String clientPhone;
    @ApiModelProperty(value = "客户姓名", required = false)
    @Column(name = "client_name")
    private String clientName;
    @ApiModelProperty(value = "厂区图片", required = false)
    @Column(name = "factory_picture")
    private String factoryPicture;
    @ApiModelProperty(value = "营业执照", required = false)
    @Column(name = "business_license")
    private String businessLicense;
    @ApiModelProperty(value = "生产经营许可证", required = false)
    @Column(name = "prod_oper_licence")
    private String prodOperLicence;
    @ApiModelProperty(value = "医疗器械许可证", required = false)
    @Column(name = "medical_dev_license")
    private String medicalDevLicense;
    @ApiModelProperty(value = "资质证明", required = false)
    @Column(name = "certification")
    private String certification;
    @ApiModelProperty(value = "产能现状及需求信息", required = false)
    @Column(name = "capcity_demand")
    private String capcityDemand;
    @ApiModelProperty(value = "熔喷布需求信息", required = false)
    @Column(name = "melt_blown_demand")
    private String meltBlownDemand;
    @ApiModelProperty(value = "经办人姓名", required = false)
    @Column(name = "operator")
    private String operator;
    @ApiModelProperty(value = "经办人电话", required = false)
    @Column(name = "operator_phone")
    private String operatorPhone;
    @ApiModelProperty(value = "申购函图片", required = false)
    @Column(name = "apply_for_picture")
    private String applyForPicture;
    @ApiModelProperty(value = "0 未审核 1 通过 2 驳回", required = false)
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

    public String getClientOpenid() {
        return clientOpenid;
    }

    public void setClientOpenid(String clientOpenid) {
        this.clientOpenid = clientOpenid;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFactoryPicture() {
        return factoryPicture;
    }

    public void setFactoryPicture(String factoryPicture) {
        this.factoryPicture = factoryPicture;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getProdOperLicence() {
        return prodOperLicence;
    }

    public void setProdOperLicence(String prodOperLicence) {
        this.prodOperLicence = prodOperLicence;
    }

    public String getMedicalDevLicense() {
        return medicalDevLicense;
    }

    public void setMedicalDevLicense(String medicalDevLicense) {
        this.medicalDevLicense = medicalDevLicense;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCapcityDemand() {
        return capcityDemand;
    }

    public void setCapcityDemand(String capcityDemand) {
        this.capcityDemand = capcityDemand;
    }

    public String getMeltBlownDemand() {
        return meltBlownDemand;
    }

    public void setMeltBlownDemand(String meltBlownDemand) {
        this.meltBlownDemand = meltBlownDemand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getApplyForPicture() {
        return applyForPicture;
    }

    public void setApplyForPicture(String applyForPicture) {
        this.applyForPicture = applyForPicture;
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
