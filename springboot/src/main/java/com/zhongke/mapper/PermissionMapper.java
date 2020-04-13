package com.zhongke.mapper;

import com.zhongke.pojo.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface PermissionMapper extends Mapper<Permission> {

    Set<Permission> findPermissionsByRoleId(Integer merchantId);
}
