package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.FacPerson;

public interface FacPersonService {
    /**
     * @Description 工厂人员信息录入
     * @author liuli
     * @date 2020/5/21 11:26
     * @param name 姓名
     * @param phone 手机号
     * @return void
     **/
    void add(String name, String phone);

    /**
     * @Description 工厂人员列表查询
     * @author liuli
     * @date 2020/5/22 10:33
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.FacPerson>
     **/
    PageInfo<FacPerson> query(String nameOrPhone, Integer status, int page, int size);

    /**
     * @Description 修改工厂人员审核状态
     * @author liuli
     * @date 2020/5/21 11:44
     * @param status 状态
     * @param id
     * @return void
     **/
    void updateById(int status, int id);
}
