package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Member
 * @Description 会员
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员实体类")
@Table(name = "zk_member")
public class Member implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "会员卡号",required = false)
    @Column(name = "memNum")
    private String memNum;
    @ApiModelProperty(value = "姓名",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "性别",required = false)
    @Column(name = "sex")
    private String sex;
    @ApiModelProperty(value = "生日",required = false)
    @Column(name = "birthday")
    private Date birthday;
    @ApiModelProperty(value = "手机号",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "卡号",required = false)
    @Column(name = "card_num")
    private String cardNum;
    @ApiModelProperty(value = "余额",required = false)
    @Column(name = "card_money")
    private BigDecimal cardMoney;
    @ApiModelProperty(value = "积分",required = false)
    @Column(name = "card_gral")
    private Integer cardGral;
    @ApiModelProperty(value = "会员卡等级id",required = false)
    @Column(name = "grade_id")
    private Integer gradeId;
    @ApiModelProperty(value = "注册时间",required = false)
    @Column(name = "register_time")
    private Date registerTime;
    @ApiModelProperty(value = "修改时间",required = false)
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String gradeName;
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;
    @Transient
    private BigDecimal lastConsum;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemNum() {
        return memNum;
    }

    public void setMemNum(String memNum) {
        this.memNum = memNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public BigDecimal getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(BigDecimal cardMoney) {
        this.cardMoney = cardMoney;
    }

    public Integer getCardGral() {
        return cardGral;
    }

    public void setCardGral(Integer cardGral) {
        this.cardGral = cardGral;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getLastConsum() {
        return lastConsum;
    }

    public void setLastConsum(BigDecimal lastConsum) {
        this.lastConsum = lastConsum;
    }
}