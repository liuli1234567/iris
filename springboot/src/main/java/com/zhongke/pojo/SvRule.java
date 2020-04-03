package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SvRule
 * @Description 会员储值规则实体类
 * @Author liuli
 * @Date 2020/4/3 11:14
 * @Version 1.0
 **/
@ApiModel(value = "SvRule",description = "会员储值规则实体类")
@Table(name = "zk_sv_rules")
public class SvRule implements Serializable {

    @ApiModelProperty(value = "储值规则id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "规则名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "储值金额",required = false)
    @Column(name = "money")
    private BigDecimal money;
    @ApiModelProperty(value = "赠送类型:1 金钱 2 积分 3 卡卷",required = false)
    @Column(name = "give_type")
    private Integer giveType;
    @ApiModelProperty(value = "储值笔数",required = false)
    @Column(name = "num")
    private Integer num;
    @ApiModelProperty(value = "状态：1 启用 2 禁用",required = false)
    @Column(name = "status")
    private Integer status;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getGiveType() {
        return giveType;
    }

    public void setGiveType(Integer giveType) {
        this.giveType = giveType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
