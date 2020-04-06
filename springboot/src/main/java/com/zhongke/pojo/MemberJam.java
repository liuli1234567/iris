package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Jam
 * @Description 会员-卡卷中间表实体类
 * @Author liuli
 * @Date 2020/4/3 18:12
 * @Version 1.0
 **/
@ApiModel(value = "MemberJam",description = "会员-卡卷中间表实体类")
@Table(name = "zk_member_jam")
public class MemberJam implements Serializable {

    @ApiModelProperty(value = "会员id",required = true)
    @Id
    @Column(name = "member_id")
    private Integer memberId;
    @ApiModelProperty(value = "卡卷id",required = true)
    @Id
    @Column(name = "jam_id")
    private Integer jamId;

    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getJamId() {
        return jamId;
    }

    public void setJamId(Integer jamId) {
        this.jamId = jamId;
    }
}
