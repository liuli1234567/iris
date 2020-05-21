package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Letter
 * @Description 用户申购函表
 * @Author liuli
 * @Date 2020/5/20 15:38
 * @Version 1.0
 **/
@ApiModel(value = "Letter",description = "用户申购函表实体类")
@Table(name = "tb_letter")
public class Letter implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "客户openid", required = false)
    @Column(name = "openid")
    private String openid;
    @ApiModelProperty(value = "用户手机号", required = false)
    @Column(name = "fac_name")
    private String facName;
    @ApiModelProperty(value = "申购函文件路径", required = false)
    @Column(name = "phone")
    private String phone;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
