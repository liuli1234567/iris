package com.zhongke.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName Role
 * @Description 角色实体类
 * @Author liuli
 * @Date 2020/3/31 11:11
 * @Version 1.0
 **/
@ApiModel(value = "Role",description = "角色")
@Table(name = "zk_role")
public class Role implements Serializable {

    @ApiModelProperty(value = "主键，角色id",required = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "角色id")
    private Integer id;
    @ApiModelProperty(value = "角色名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "角色关键字",required = false)
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

    @ApiModelProperty(value = "权限集合",required = false)
    @Transient
    private Set<Permission> permissions = new HashSet<Permission>(0);
    @ApiModelProperty(value = "菜单集合",required = false)
    @Transient
    private LinkedHashSet<Menu> menus = new LinkedHashSet<Menu>(0);

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public LinkedHashSet<Menu> getMenus() {
        return menus;
    }

    public void setMenus(LinkedHashSet<Menu> menus) {
        this.menus = menus;
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
}
