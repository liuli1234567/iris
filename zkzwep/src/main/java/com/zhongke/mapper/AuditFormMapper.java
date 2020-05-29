package com.zhongke.mapper;

import com.zhongke.pojo.AuditForm;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditFormMapper extends Mapper<AuditForm> {
    /**
     * @Description 客户资料审核表列表查询
     * @author liuli
     * @date 2020/5/21 13:42
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param startTime 资料创建起始时间
     * @param endTime 资料创建结束时间
     * @return java.util.List<com.zhongke.pojo.AuditForm>
     **/
    List<AuditForm> findAll(@Param("nameOrPhone") String nameOrPhone, @Param("status") Integer status,
                            @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * @Description 根据用户openid查询用户信息
     * @author liuli
     * @date 2020/5/25 10:07
     * @param openid
     * @return com.zhongke.pojo.AuditForm
     **/
    AuditForm findByOpenid(String openid);
}
