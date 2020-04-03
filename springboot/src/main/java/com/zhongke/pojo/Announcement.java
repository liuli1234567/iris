package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Announcement
 * @Description 公告实体类
 * @Author liuli
 * @Date 2020/4/1 11:33
 * @Version 1.0
 **/
@ApiModel(value = "Announcement",description = "公告实体类")
@Table(name = "zk_announcement")
public class Announcement implements Serializable {
    @ApiModelProperty(value = "公告id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "公告标题",required = false)
    @Column(name = "title")
    private String title;
    @ApiModelProperty(value = "公告内容",required = false)
    @Column(name = "body")
    private String body;
    @ApiModelProperty(value = "通知对象",required = false)
    @Column(name = "notice_obj")
    private String noticeObj;
    @ApiModelProperty(value = "下载内容",required = false)
    @Column(name = "download")
    private String download;
    @ApiModelProperty(value = "发布时间",required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNoticeObj() {
        return noticeObj;
    }

    public void setNoticeObj(String noticeObj) {
        this.noticeObj = noticeObj;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
