package com.zhongke.service;

import com.zhongke.pojo.PlatformUser;

public interface UserLoginService {
    /**
     * @Description 设备用户登录
     * @author liuli
     * @date 2020/4/28 11:42
     * @param username
     * @param password
     * @return com.zhongke.pojo.PlatformUser
     **/
    PlatformUser login(String username,String password);
}
