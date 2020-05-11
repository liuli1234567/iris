package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Announcement;
import com.zhongke.pojo.Menu;
import com.zhongke.pojo.PlatformUser;

import java.util.List;

public interface PlatformUserService {

    /**
     * @Description 根据条件查询平台用户并分页
     * @author liuli
     * @date 2020/4/2 18:10
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.PlatformUser>
     **/
    PageInfo<PlatformUser> platUsers(String name, String tel ,int page, int size);

    /**
     * @Description 更新用户
     * @author liuli
     * @date 2020/4/2 18:11
     * @param platformUser
     * @param id
     * @return void
     **/
    void update(PlatformUser platformUser, int id);

    /**
     * @Description 根据id查询
     * @author liuli
     * @date 2020/4/2 18:24
     * @param id
     * @return void
     **/
    PlatformUser findById(int id);

    /**
     * @Description 查询平台用户拥有的权限信息
     * @author liuli
     * @date 2020/4/13 15:22
     * @param platformUserName
     * @return com.zhongke.pojo.PlatformUser
     **/
    PlatformUser findByPlatformUserName(String platformUserName);

    /**
     * @Description 添加用户
     * @author liuli
     * @date 2020/5/9 11:03
     * @param platformUser
     * @return void
     **/
    void add(PlatformUser platformUser);

    /**
     * @Description springseurity验证成功后跳转首页加载菜单
     * @author liuli
     * @date 2020/5/11 16:58
     * @param
     * @return java.util.List<com.zhongke.pojo.Menu>
     **/
    List<Menu> getMenu();

}
