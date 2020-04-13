package com.zhongke.service.impl;


import com.zhongke.mapper.PermissionMapper;
import com.zhongke.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PermissionServiceImpl
 * @Description 权限
 * @Author liuli
 * @Date 2020/4/13 10:25
 * @Version 1.0
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private PermissionMapper permissionMapper;
}
