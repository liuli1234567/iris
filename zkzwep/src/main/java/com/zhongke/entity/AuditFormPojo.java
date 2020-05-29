package com.zhongke.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName AuditFormPojo
 * @Description 公众号客户提交资料封装实体类
 * @Author liuli
 * @Date 2020/5/26 14:15
 * @Version 1.0
 **/
@Component
public class AuditFormPojo {

    private String clientOpenid; // 用户openid
    private String clientPhone; // 手机号
    private String clientName; // 姓名
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<String> businessList; // 营业执照图片数组
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<String> prodOperList; // 生产经营许可证图片数组
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<String> medicalDevList; // 医疗器械许可证图片数组
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String letter; // 申购函文件

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

    public List<String> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<String> businessList) {
        this.businessList = businessList;
    }

    public List<String> getProdOperList() {
        return prodOperList;
    }

    public void setProdOperList(List<String> prodOperList) {
        this.prodOperList = prodOperList;
    }

    public List<String> getMedicalDevList() {
        return medicalDevList;
    }

    public void setMedicalDevList(List<String> medicalDevList) {
        this.medicalDevList = medicalDevList;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
