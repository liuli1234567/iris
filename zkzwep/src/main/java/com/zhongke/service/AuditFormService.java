package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.AuditForm;

public interface AuditFormService {
    /**
     * @Description 客户资料审核表列表查询
     * @author liuli
     * @date 2020/5/21 13:42
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param startTime 资料创建起始时间
     * @param endTime 资料创建结束时间
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.AuditForm>
     **/
    PageInfo<AuditForm> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size);

    /**
     * @Description 审核客户资料
     * @author liuli
     * @date 2020/5/21 16:01
     * @param id
     * @param status  状态
     * @return void
     **/
    void update(int id, int status);

    /**
     * @Description 公众号客户提交审核资料
     * @author liuli
     * @date 2020/5/22 16:00
     * @param openid 用户openid
     * @param phone 手机号
     * @param name 姓名
     * @param businessLicense 营业执照图片数组
     * @param prodOperLicense 生产经营许可证图片数组
     * @param medicalDevLicense 医疗器械许可证图片数组
     * @param letter 申购函文件
     * @return void
     **/
    void add(String openid, String phone, String name, String[] businessLicense, String[] prodOperLicense, String[] medicalDevLicense, String letter);
}
