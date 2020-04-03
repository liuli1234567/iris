package com.zhongke.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Merchant
 * @Description 商户实体类
 * @Author liuli
 * @Date 2020/4/1 16:59
 * @Version 1.0
 **/
@Table(name = "zk_merchant")
public class Merchant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 商户id
    @Column(name = "name")
    private String name; // 商户名称
    @Column(name = "number")
    private String number; // 商户号
    @Column(name = "payee_name")
    private String payeeName; // 商户收款人姓名
    @Column(name = "payee_bank")
    private String payeeBank; // 商户收款人银行卡号
    @Column(name = "bank_name")
    private String bankName; // 开户行
    @Column(name = "address")
    private String address; // 商户地址
    @Column(name = "tel")
    private String tel; // 商户电话
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
