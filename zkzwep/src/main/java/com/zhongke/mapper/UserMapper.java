package com.zhongke.mapper;

import com.zhongke.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
     * @Description 后台系统用户列表
     * @author liuli
     * @date 2020/5/21 17:42
     * @param nameOrPhone 用户名或手机号
     * @param role 角色
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.User>
     **/
    List<User> findAll(@Param("nameOrPhone") String nameOrPhone, @Param("role") Integer role);

    /**
     * @Description 通过名字查询用户
     * @author liuli
     * @date 2020/5/22 16:17
     * @param username
     * @return com.zhongke.pojo.User
     **/
    User findUserByName(String username);
}
