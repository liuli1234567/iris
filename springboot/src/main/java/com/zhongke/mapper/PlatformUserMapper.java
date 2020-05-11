package com.zhongke.mapper;

import com.zhongke.pojo.PlatformUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PlatformUserMapper extends Mapper<PlatformUser> {

    /**
     * @Description 查询平台用户拥有的权限
     * @author liuli
     * @date 2020/4/13 15:21
     * @param platformUserName
     * @return com.zhongke.pojo.PlatformUser
     **/
    PlatformUser findByPlatformUserName(String platformUserName);

    /**
     * @Description 通过条件查询所有用户并分页
     * @author liuli
     * @date 2020/5/11 18:20
     * @param name
     * @param tel
     * @return java.util.List<com.zhongke.pojo.PlatformUser>
     **/
    List<PlatformUser> selectAllByExample(@Param("name") String name, @Param("tel") String tel);
}
