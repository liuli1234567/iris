package com.zhongke.service.impl;

import com.zhongke.mapper.PlatformUserMapper;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


/**
 * @ClassName UserLoginServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/4/28 11:42
 * @Version 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired(required = false)
    private PlatformUserMapper platformUserMapper;
    @Override
    public PlatformUser login(String username, String password) {
        Example example = new Example(PlatformUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",username);
        criteria.andEqualTo("password",password);
        PlatformUser platformUser = platformUserMapper.selectOneByExample(example);
        return platformUser;
    }
}
