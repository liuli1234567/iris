package com.zhongke.service.impl;

import com.zhongke.mapper.RoleMapper;
import com.zhongke.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色
 * @Author liuli
 * @Date 2020/4/13 10:21
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;
}
