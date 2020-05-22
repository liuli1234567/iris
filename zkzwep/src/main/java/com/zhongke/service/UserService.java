package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.User;

public interface UserService {

    /**
     * @Description 后台系统登录
     * @author liuli
     * @date 2020/5/21 17:43
     * @param u 登录对象
     * @return com.zhongke.pojo.User
     **/
    User login(User u);

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
    PageInfo<User> findAll(String nameOrPhone, Integer role, int page, int size);

    /**
     * @Description 添加系统登录用户
     * @author liuli
     * @date 2020/5/21 17:51
     * @param username 用户名
     * @param password 密码
     * @param role 角色
     * @return void
     **/
    void add(String username, String password, int role);

    /**
     * @Description 根据id删除用户
     * @author liuli
     * @date 2020/5/21 17:57
     * @param id
     * @return void
     **/
    void deleteById(int id);

    /**
     * @Description 根据id更新用户
     * @author liuli
     * @date 2020/5/21 18:03
     * @param user 用户对象
     * @return void
     **/
    void updateById(User user);
}
