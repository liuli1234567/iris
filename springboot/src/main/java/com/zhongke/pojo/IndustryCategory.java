package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName IndustryCategory
 * @Description 行业列表
 * @Author liuli
 * @Date 2020/4/3 18:12
 * @Version 1.0
 **/
@ApiModel(value = "IndustryCategory",description = "卡卷实体类")
@Table(name = "zk_industry_category")
public class IndustryCategory implements Serializable {

    @ApiModelProperty(value = "行业id",required = false)
    @Id
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "行业名称",required = false)
    @Column(name = "name")
    private String name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
