package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.UserMapper;
import com.zhongke.pojo.User;
import com.zhongke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
        User user = userMapper.findUserByName(u.getUsername());
        if(user!=null&&bCryptPasswordEncoder.matches(u.getPassword(),user.getPassword())){
            return user;
        }
        return null;
    }

    /**
     * @Description 后台系统用户列表
     * @author liuli
     * @date 2020/5/21 17:42
     * @param nameOrPhone 用户名或手机号
     * @param role 角色
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.User>
     **/
    @Override
    public PageInfo<User> findAll(String nameOrPhone, Integer role, int page, int size) {
        PageHelper.startPage(page,size);
        List<User> users = userMapper.findAll(nameOrPhone,role);
        return new PageInfo<>(users);
    }

    /**
     * @Description 添加系统登录用户
     * @author liuli
     * @date 2020/5/21 17:51
     * @param username 用户名
     * @param password 密码
     * @param role 角色
     * @return int
     **/
    @Override
    public int add(String username, String password, int role) {
        User user = new User();
        user.setUsername(username);
        User use = userMapper.findUserByName(username);
        if (use != null) {
            return -1;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(role);
        user.setUpdatetime(new Date());
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
        return 0;
    }

    /**
     * @Description 根据id删除用户
     * @author liuli
     * @date 2020/5/21 17:57
     * @param id
     * @return void
     **/
    @Override
    public void deleteById(int id) {
        User user = new User();
        user.setId(id);
        userMapper.deleteByPrimaryKey(user);
    }

    /**
     * @Description 根据id更新用户
     * @author liuli
     * @date 2020/5/21 18:03
     * @param user 用户对象
     * @return void
     **/
    @Override
    public void updateById(User user) {
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

}
