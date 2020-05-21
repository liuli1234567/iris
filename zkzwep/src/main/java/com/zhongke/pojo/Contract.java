package com.zhongke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Contract
 * @Description 合同表
 * @Author liuli
 * @Date 2020/5/20 15:38
 * @Version 1.0
 **/
@ApiModel(value = "Contract",description = "客户合同表实体类")
@Table(name = "tb_contract")
public class Contract implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "客户openid", required = false)
    @Column(name = "client_openid")
    private String clientOpenid;
    @ApiModelProperty(value = "客户手机号", required = false)
    @Column(name = "client_phone")
    private String clientPhone;
    @ApiModelProperty(value = "只有客户章合同", required = false)
    @Column(name = "contract_client")
    private String contractClient;
    @ApiModelProperty(value = "双方章合同", required = false)
    @Column(name = "contract_all")
    private String contractAll;
    @ApiModelProperty(value = "0 未审核 1 通过 2 驳回", required = false)
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "updatetime")
    private Date updatetime;
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "createtime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientOpenid() {
        return clientOpenid;
    }

    public void setClientOpenid(String clientOpenid) {
        this.clientOpenid = clientOpenid;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getContractClient() {
        return contractClient;
    }

    public void setContractClient(String contractClient) {
        this.contractClient = contractClient;
    }

    public String getContractAll() {
        return contractAll;
    }

    public void setContractAll(String contractAll) {
        this.contractAll = contractAll;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
