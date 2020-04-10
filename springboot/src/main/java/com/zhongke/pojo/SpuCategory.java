package com.zhongke.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SpuCategory
 * @Description 商品分类
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@ApiModel(value = "SpuCategory",description = "商品实体类")
@Table(name = "zk_spu_category")
public class SpuCategory implements Serializable {
    @ApiModelProperty(value = "分类id",required = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "分类名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "分类级别",required = false)
    @Column(name = "level")
    private Integer level;
    @ApiModelProperty(value = "商品数量",required = false)
    @Column(name = "spu_num")
    private Integer spuNum;
    @ApiModelProperty(value = "数量单位",required = false)
    @Column(name = "quantity_unit")
    private String quantityUnit;
    @ApiModelProperty(value = "排序",required = false)
    @Column(name = "sort")
    private Integer sort;
    @ApiModelProperty(value = "上级id",required = false)
    @Column(name = "parent_id")
    private Integer parentId;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSpuNum() {
        return spuNum;
    }

    public void setSpuNum(Integer spuNum) {
        this.spuNum = spuNum;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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