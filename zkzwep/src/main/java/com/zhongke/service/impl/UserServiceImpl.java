package com.zhongke.service.impl;

import com.zhongke.mapper.UserMapper;
import com.zhongke.pojo.User;
import com.zhongke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:11
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User login(User u) {
        User user1 = new User();
        user1.setUsername(u.getUsername());
        User user = userMapper.selectOne(user1);
        if(user!=null&&bCryptPasswordEncoder.matches(u.getPassword(),user.getPassword())){
            return user;
        }
        return null;
    }
}
