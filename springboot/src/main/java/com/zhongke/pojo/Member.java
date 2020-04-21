package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Member
 * @Description 会员
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@ApiModel(value = "Member",description = "会员实体类")
@Table(name = "zk_member")
public class Member implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "会员号",required = false)
    @Column(name = "mem_no")
    private String memNo;
    @ApiModelProperty(value = "姓名",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "昵称",required = false)
    @Column(name = "nick_name")
    private String nickName;
    @ApiModelProperty(value = "头像",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "性别",required = false)
    @Column(name = "sex")
    private String sex;
    @ApiModelProperty(value = "生日",required = false)
    @Column(name = "birthday")
    private String birthday;
    @ApiModelProperty(value = "手机号",required = false)
    @Column(name = "tel")
    private String tel;
    @ApiModelProperty(value = "卡号",required = false)
    @Column(name = "card_no")
    private String cardNo;
    @ApiModelProperty(value = "余额",required = false)
    @Column(name = "card_money")
    private BigDecimal cardMoney;
    @ApiModelProperty(value = "积分",required = false)
    @Column(name = "card_gral")
    private Integer cardGral;
    @ApiModelProperty(value = "会员卡等级id",required = false)
    @Column(name = "grade_id")
    private Integer gradeId;
    @ApiModelProperty(value = "会员卡等级名称",required = false)
    @Column(name = "grade_name")
    private String gradeName;
    @ApiModelProperty(value = "注册时间",required = false)
    @Column(name = "register_time")
    private Date registerTime;
    @ApiModelProperty(value = "修改时间",required = false)
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String startTime;
    @Transient
    private String endTime;
    @Transient
    private BigDecimal lastConsum;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getLastConsum() {
        return lastConsum;
    }

    public void setLastConsum(BigDecimal lastConsum) {
        this.lastConsum = lastConsum;
    }
}