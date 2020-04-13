package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @ClassName Permission
 * @Description 权限实体类
 * @Author liuli
 * @Date 2020/3/31 11:11
 * @Version 1.0
 **/
@ApiModel(value = "Permission",description = "权限")
@Table(name = "zk_permission")
public class Permission implements Serializable {

    @ApiModelProperty(value = "主键，权限id",required = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "权限id")
    private Integer id;
    @ApiModelProperty(value = "权限名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "权限关键字",required = false)
    @Column(name = "keyword")
    private String keyword;
    @ApiModelProperty(value = "描述",required = false)
    @Column(name = "description")
    private String description;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
    private Date createTime;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    @ApiModelProperty(value = "角色集合",required = false)
    private Set<Role> roles = new HashSet<Role>(0);

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
