package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Cashier
 * @Description 收银员实体类
 * @Author liuli
 * @Date 2020/4/1 11:33
 * @Version 1.0
 **/
@ApiModel(value = "Cashier",description = "收银员实体类")
@Table(name = "zk_cashier")
public class Cashier implements Serializable {
    @ApiModelProperty(value = "收银员id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 收银员id
    @ApiModelProperty(value = "收银员名字",required = false)
    @Column(name = "name")
    private String name; // 收银员名字
    @ApiModelProperty(value = "收银员电话",required = false)
    @Column(name = "tel")
    private String tel; // 收银员电话
    @ApiModelProperty(value = "收银员状态：y 在职 n 离职",required = false)
    @Column(name = "status")
    private String status; // 状态（y 在职 n 离职）
    @ApiModelProperty(value = "收银员所属门店id",required = false)
    @Column(name = "store_id")
    private String storeId; // 收银员所属门店id
    @ApiModelProperty(value = "更新时间",required = false)
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
