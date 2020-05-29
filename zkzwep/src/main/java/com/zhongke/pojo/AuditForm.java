package com.zhongke.pojo;

import com.sun.javafx.collections.MappingChange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @ApiModelProperty(value = "营业执照", required = false)
    @Column(name = "business_license")
    private String businessLicense;
    @ApiModelProperty(value = "生产经营许可证", required = false)
    @Column(name = "prod_oper_licence")
    private String prodOperLicence;
    @ApiModelProperty(value = "医疗器械许可证", required = false)
    @Column(name = "medical_dev_license")
    private String medicalDevLicense;
    @ApiModelProperty(value = "0 未审核 1 通过 2 驳回", required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "申购函url", required = false)
    @Column(name = "letter_url")
    private String letterUrl;
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "updatetime")
    private Date updatetime;
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    private String[] businessArray;
    @Transient
    private String[] prodOperArray;
    @Transient
    private String[] medicalDevArray;

    @Transient
    private List<Map<String,String>> businessList;
    @Transient
    private List<Map<String,String>> prodOperList;
    @Transient
    private List<Map<String,String>> medicalDevList;

    public List<Map<String, String>> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Map<String, String>> businessList) {
        this.businessList = businessList;
    }

    public List<Map<String, String>> getProdOperList() {
        return prodOperList;
    }

    public void setProdOperList(List<Map<String, String>> prodOperList) {
        this.prodOperList = prodOperList;
    }

    public List<Map<String, String>> getMedicalDevList() {
        return medicalDevList;
    }

    public void setMedicalDevList(List<Map<String, String>> medicalDevList) {
        this.medicalDevList = medicalDevList;
    }

    public String[] getBusinessArray() {
        return businessArray;
    }

    public void setBusinessArray(String[] businessArray) {
        this.businessArray = businessArray;
    }

    public String[] getProdOperArray() {
        return prodOperArray;
    }

    public void setProdOperArray(String[] prodOperArray) {
        this.prodOperArray = prodOperArray;
    }

    public String[] getMedicalDevArray() {
        return medicalDevArray;
    }

    public void setMedicalDevArray(String[] medicalDevArray) {
        this.medicalDevArray = medicalDevArray;
    }

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

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getProdOperLicence() {
        return prodOperLicence;
    }

    public String getLetterUrl() {
        return letterUrl;
    }

    public void setLetterUrl(String letterUrl) {
        this.letterUrl = letterUrl;
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
