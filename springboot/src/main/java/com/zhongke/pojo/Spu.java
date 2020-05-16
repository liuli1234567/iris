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
 * @ClassName Spu
 * @Description 商品
 * @Author liuli
 * @CreateDate 2020/4/7
 * @Version 2.1
 **/
@ApiModel(value = "Spu",description = "商品实体类")
@Table(name = "zk_spu")
public class Spu implements Serializable {
    @ApiModelProperty(value = "主键，商品id",required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ApiModelProperty(value = "商品编号",required = false)
    @Column(name = "no")
    private String no;
    @ApiModelProperty(value = "商品名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "商品价格",required = false)
    @Column(name = "price")
    private BigDecimal price;
    @ApiModelProperty(value = "库存数量",required = false)
    @Column(name = "num")
    private Integer num;
    @ApiModelProperty(value = "计价单位",required = false)
    @Column(name = "sales_unit")
    private String salesUnit;
    @ApiModelProperty(value = "销量",required = false)
    @Column(name = "sale_num")
    private Integer saleNum;
    @ApiModelProperty(value = "商品条码",required = false)
    @Column(name = "sn")
    private String sn;
    @ApiModelProperty(value = "商品图片",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "商品图片集合",required = false)
    @Column(name = "images")
    private String images;
    @ApiModelProperty(value = "1 上架 -1 下架",required = false)
    @Column(name = "is_marketable")
    private Integer isMarketable;
   /* @ApiModelProperty(value = "商品分类id",required = true)
    @Column(name = "category_id")
    private Integer categoryId;*/
   @ApiModelProperty(value = "商品分类名称",required = true)
   @Column(name = "category_name")
   private String categoryName;
    @ApiModelProperty(value = "备注",required = false)
    @Column(name = "remarks")
    private String remarks;
    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "updatetime")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @ApiModelProperty(value = "小计",required = false)
    @Transient
    private BigDecimal spuSum;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BigDecimal getSpuSum() {
        return spuSum;
    }

    public void setSpuSum(BigDecimal spuSum) {
        this.spuSum = spuSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(Integer isMarketable) {
        this.isMarketable = isMarketable;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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