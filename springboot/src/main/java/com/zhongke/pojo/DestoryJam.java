package com.zhongke.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import sun.awt.SunHints;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DestoryJam
 * @Description 核销卡卷
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "核销卡卷实体类")
@Table(name = "zk_destory_jam")
public class DestoryJam implements Serializable {
    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "卡卷id",required = false)
    @Column(name = "jam_id")
    private Integer jamId;
    @ApiModelProperty(value = "卡卷名称",required = false)
    @Column(name = "jam_name")
    private String jamName;
    @ApiModelProperty(value = "核销人",required = false)
    @Column(name = "destory_name")
    private String destoryName;
    @ApiModelProperty(value = "核销时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    @Transient
    private String numName;
    @Transient
    private String startTime;
    @Transient
    private String endTime;

    public String getJamName() {
        return jamName;
    }

    public void setJamName(String jamName) {
        this.jamName = jamName;
    }

    public String getNumName() {
        return numName;
    }

    public void setNumName(String numName) {
        this.numName = numName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJamId() {
        return jamId;
    }

    public void setJamId(Integer jamId) {
        this.jamId = jamId;
    }

    public String getDestoryName() {
        return destoryName;
    }

    public void setDestoryName(String destoryName) {
        this.destoryName = destoryName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}