package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Product
 * @Description 产品信息表
 * @Author liuli
 * @Date 2020/5/20 15:38
 * @Version 1.0
 **/
@ApiModel(value = "Product",description = "产品信息表实体类")
@Table(name = "tb_product")
public class Product implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "产品名称", required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "库存", required = false)
    @Column(name = "sku")
    private Integer sku;
    @ApiModelProperty(value = "入库时间", required = false)
    @Column(name = "input_time")
    private Date inputTime;
    @ApiModelProperty(value = "入库吨数", required = false)
    @Column(name = "stock_in")
    private Integer stockIn;
    @ApiModelProperty(value = "出库吨数", required = false)
    @Column(name = "stock_out")
    private Integer stockOut;
    @ApiModelProperty(value = "结存", required = false)
    @Column(name = "stock")
    private Integer stock;
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "updatetime")
    private Date updatetime;

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

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getStockIn() {
        return stockIn;
    }

    public void setStockIn(Integer stockIn) {
        this.stockIn = stockIn;
    }

    public Integer getStockOut() {
        return stockOut;
    }

    public void setStockOut(Integer stockOut) {
        this.stockOut = stockOut;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
