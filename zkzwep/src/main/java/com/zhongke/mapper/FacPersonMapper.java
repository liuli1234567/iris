package com.zhongke.mapper;

import com.zhongke.pojo.FacPerson;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FacPersonMapper extends Mapper<FacPerson> {
    /**
     * @Description
     * @author liuli
     * @date 2020/5/22 9:32
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @return java.util.List<com.zhongke.pojo.FacPerson>
     **/
    List<FacPerson> queryAll(@Param("nameOrPhone") String nameOrPhone, @Param("status") Integer status);
}
