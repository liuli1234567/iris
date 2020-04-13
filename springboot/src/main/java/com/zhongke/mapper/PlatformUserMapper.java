package com.zhongke.mapper;

import com.zhongke.pojo.PlatformUser;
import tk.mybatis.mapper.common.Mapper;

public interface PlatformUserMapper extends Mapper<PlatformUser> {

    /**
     * @Description 查询平台用户拥有的权限
     * @author liuli
     * @date 2020/4/13 15:21
     * @param platformUserName
     * @return com.zhongke.pojo.PlatformUser
     **/
    PlatformUser findByPlatformUserName(String platformUserName);
}
