package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.AuditFormPojo;
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
     * @date 2020/5/26 14:49
     * @param auditFormPojo 参数封装实体类
     * @return void
     **/
    void add(AuditFormPojo auditFormPojo);

    /**
     * @Description 公众号回显用户审核资料
     * @author liuli
     * @date 2020/5/26 15:54
     * @param openid
     * @return com.zhongke.pojo.AuditForm
     **/
    AuditForm findByOpenid(String openid);
}
