package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FacPerson
 * @Description 工厂人员表
 * @Author liuli
 * @Date 2020/5/20 15:38
 * @Version 1.0
 **/
@ApiModel(value = "FacPerson",description = "工厂人员表实体类")
@Table(name = "tb_fac_person")
public class FacPerson implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "客户openid", required = false)
    @Column(name = "openid")
    private String openid;
    @ApiModelProperty(value = "姓名", required = false)
    @Column(name = "fac_name")
    private String facName;
    @ApiModelProperty(value = "手机号", required = false)
    @Column(name = "phone")
    private String phone;
    @ApiModelProperty(value = "0 未审核 1 通过 2 驳回", required = false)
    @Column(name = "status")
    private Integer status;
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

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
