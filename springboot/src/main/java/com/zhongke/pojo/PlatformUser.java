package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PlatformUser
 * @Description 平台用户实体类
 * @Author liuli
 * @Date 2020/4/1 11:33
 * @Version 1.0
 **/
@ApiModel(value = "PlatformUser",description = "平台用户实体类")
@Table(name = "zk_platform_user")
public class PlatformUser implements Serializable {
    @ApiModelProperty(value = "用户id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "用户姓名",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "用户密码",required = false)
    @Column(name = "password")
    private String password;
    @ApiModelProperty(value = "用户昵称",required = false)
    @Column(name = "nickname")
    private String nickname;
    @ApiModelProperty(value = "用户所属商户id",required = false)
    @Column(name = "merchant_id")
    private String merchantId;
    @ApiModelProperty(value = "性别",required = false)
    @Column(name = "sex")
    private String sex;
    @ApiModelProperty(value = "状态",required = false)
    @Column(name = "status")
    private String status;
    @ApiModelProperty(value = "手机号",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "图片url",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updatetime;
    @ApiModelProperty(value = "注册时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    @ApiModelProperty(value = "角色集合",required = false)
    private Set<Role> roles = new HashSet<Role>(0);

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
