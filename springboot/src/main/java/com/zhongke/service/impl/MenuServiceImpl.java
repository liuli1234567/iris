package com.zhongke.service.impl;

import com.zhongke.mapper.MenuMapper;
import com.zhongke.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MenuServiceImpl
 * @Description 菜单
 * @Author liuli
 * @Date 2020/4/13 10:23
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired(required = false)
    private MenuMapper menuMapper;
}
