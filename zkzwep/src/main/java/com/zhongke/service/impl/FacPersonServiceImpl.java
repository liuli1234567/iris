package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.FacPersonMapper;
import com.zhongke.pojo.FacPerson;
import com.zhongke.service.FacPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FacPersonServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:09
 * @Version 1.0
 **/
@Service
public class FacPersonServiceImpl implements FacPersonService {

    @Autowired(required = false)
    private FacPersonMapper facPersonMapper;

    /**
     * @Description 工厂人员信息录入
     * @author liuli
     * @date 2020/5/21 11:26
     * @param name 姓名
     * @param phone 手机号
     * @return void
     **/
    @Override
    public void add(String name, String phone) {
        FacPerson facPerson = new FacPerson();
        facPerson.setFacName(name);
        facPerson.setPhone(phone);
        facPerson.setStatus(0);
        facPerson.setCreateTime(new Date());
        facPersonMapper.insertSelective(facPerson);
    }

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
    @Override
    public PageInfo<FacPerson> query(String nameOrPhone, Integer status, int page, int size) {
        PageHelper.startPage(page,size);
        List<FacPerson> facPersonList = facPersonMapper.queryAll(nameOrPhone,status);
        return new PageInfo<>(facPersonList);
    }

    /**
     * @Description 修改工厂人员审核状态
     * @author liuli
     * @date 2020/5/21 11:45
     * @param status 状态
     * @param id
     * @return void
     **/
    @Override
    public void updateById(int status, int id) {
        FacPerson facPerson = new FacPerson();
        facPerson.setId(id);
        if (2 == status){
            facPersonMapper.deleteByPrimaryKey(facPerson);
        }
        facPerson.setStatus(status);
        facPersonMapper.updateByPrimaryKeySelective(facPerson);
    }
}
