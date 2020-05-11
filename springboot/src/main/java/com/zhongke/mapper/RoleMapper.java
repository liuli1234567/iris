package com.zhongke.mapper;

import com.zhongke.pojo.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface RoleMapper extends Mapper<Role> {

    Role findRoleByRoleName(String roleName);

}
