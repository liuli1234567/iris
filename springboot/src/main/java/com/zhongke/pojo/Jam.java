package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Jam
 * @Description 卡卷实体类
 * @Author liuli
 * @Date 2020/4/3 18:12
 * @Version 1.0
 **/
@ApiModel(value = "Jam",description = "卡卷实体类")
@Table(name = "zk_jam")
public class Jam implements Serializable {

    @ApiModelProperty(value = "卡卷id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "金额",required = false)
    @Column(name = "money")
    private BigDecimal money;
    @ApiModelProperty(value = "卡卷logo url",required = false)
    @Column(name = "logo_url")
    private String logoUrl;
    @ApiModelProperty(value = "类型：1 立即生效 2 时间段生效",required = false)
    @Column(name = "valid_type")
    private Integer validType;
    @ApiModelProperty(value = "有效天数",required = false)
    @Column(name = "claimed_day")
    private Integer claimedDay;
    @ApiModelProperty(value = "有效开始时间",required = false)
    @Column(name = "eff_start_time")
    private Date effStartTime;
    @ApiModelProperty(value = "有效结束时间",required = false)
    @Column(name = "eff_end_time")
    private Date effEndTime;
    @ApiModelProperty(value = "可领取开始时间",required = false)
    @Column(name = "clai_start_time")
    private Date claiStartTime;
    @ApiModelProperty(value = "可领取结束时间",required = false)
    @Column(name = "clai_end_time")
    private Integer claiEndTime;
    @ApiModelProperty(value = "发行数量",required = false)
    @Column(name = "num")
    private Integer num;
    @ApiModelProperty(value = "卡卷说明",required = false)
    @Column(name = "description")
    private String description;
    @ApiModelProperty(value = "最低消费",required = false)
    @Column(name = "min_consum")
    private BigDecimal minConsum;
    @ApiModelProperty(value = "使用星期段：‘0123456’星期一到星期日",required = false)
    @Column(name = "week")
    private String week;
    @ApiModelProperty(value = "使用时间段开始时间",required = false)
    @Column(name = "period_start")
    private Integer periodStart;
    @ApiModelProperty(value = "使用时间段结束时间",required = false)
    @Column(name = "period_end")
    private Integer periodEnd;
    @ApiModelProperty(value = "用户限领数：-1 不限制",required = false)
    @Column(name = "user_num")
    private Integer userNum;
    @ApiModelProperty(value = "过期提醒：1 开启 2 关闭",required = false)
    @Column(name = "exp_remind")
    private Integer expRemind;
    @ApiModelProperty(value = "发布至微信卡包：1 发布 2 不发布",required = false)
    @Column(name = "post_wechat")
    private Integer postWechat;
    @ApiModelProperty(value = "卡卷颜色",required = false)
    @Column(name = "card_color")
    private String cardColor;
    @ApiModelProperty(value = "门店id集合",required = false)
    @Column(name = "store_ids")
    private String storeIds;
    @ApiModelProperty(value = "商户名字",required = false)
    @Column(name = "merchant_name")
    private String merchantName;
    @ApiModelProperty(value = "卡卷标题",required = false)
    @Column(name = "title")
    private String title;
    @ApiModelProperty(value = "更新日期",required = false)
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getValidType() {
        return validType;
    }

    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    public Integer getClaimedDay() {
        return claimedDay;
    }

    public void setClaimedDay(Integer claimedDay) {
        this.claimedDay = claimedDay;
    }

    public Date getEffStartTime() {
        return effStartTime;
    }

    public void setEffStartTime(Date effStartTime) {
        this.effStartTime = effStartTime;
    }

    public Date getEffEndTime() {
        return effEndTime;
    }

    public void setEffEndTime(Date effEndTime) {
        this.effEndTime = effEndTime;
    }

    public Date getClaiStartTime() {
        return claiStartTime;
    }

    public void setClaiStartTime(Date claiStartTime) {
        this.claiStartTime = claiStartTime;
    }

    public Integer getClaiEndTime() {
        return claiEndTime;
    }

    public void setClaiEndTime(Integer claiEndTime) {
        this.claiEndTime = claiEndTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinConsum() {
        return minConsum;
    }

    public void setMinConsum(BigDecimal minConsum) {
        this.minConsum = minConsum;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Integer periodStart) {
        this.periodStart = periodStart;
    }

    public Integer getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Integer periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getExpRemind() {
        return expRemind;
    }

    public void setExpRemind(Integer expRemind) {
        this.expRemind = expRemind;
    }

    public Integer getPostWechat() {
        return postWechat;
    }

    public void setPostWechat(Integer postWechat) {
        this.postWechat = postWechat;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
