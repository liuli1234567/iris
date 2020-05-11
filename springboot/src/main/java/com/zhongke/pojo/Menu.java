package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @ClassName Menu
 * @Description 菜单实体类
 * @Author liuli
 * @Date 2020/3/31 11:11
 * @Version 1.0
 **/
@ApiModel(value = "Menu",description = "菜单")
@Table(name = "zk_menu")
public class Menu implements Serializable {

    @ApiModelProperty(value = "主键，菜单id",required = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "菜单id")
    private Integer id;
    @ApiModelProperty(value = "菜单名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "访问路径",required = false)
    @Column(name = "linkUrl")
    private String linkUrl;
    @ApiModelProperty(value = "菜单项所对应的路由路径",required = false)
    @Column(name = "path")
    private String path;
    /*@ApiModelProperty(value = "优先级",required = false)
    @Column(name = "priority")
    private Integer priority;*/
    @ApiModelProperty(value = "排序",required = false)
    @Column(name = "sort")
    private Integer sort;
    @ApiModelProperty(value = "图标",required = false)
    @Column(name = "icon")
    private String icon;
    @ApiModelProperty(value = "描述",required = false)
    @Column(name = "description")
    private String description;
    @ApiModelProperty(value = "父菜单id",required = false)
    @Column(name = "parentMenuId")
    private Integer parentMenuId;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
    private Date createTime;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    @ApiModelProperty(value = "角色集合",required = false)
    private Set<Role> roles = new HashSet<Role>(0);
    @Transient
    @ApiModelProperty(value = "子菜单集合",required = false)
    private List<Menu> children = new ArrayList<>();

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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
