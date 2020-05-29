package com.zhongke.mapper;

import com.zhongke.pojo.Contract;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ContractMapper extends Mapper<Contract> {

    /**
     * @Description 客户合同列表查询
     * @author liuli
     * @date 2020/5/21 13:42
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param startTime 合同创建起始时间
     * @param endTime 合同创建结束时间
     * @return java.util.List<com.zhongke.pojo.Contract>
     **/
    List<Contract> findAll(@Param("nameOrPhone") String nameOrPhone, @Param("status") Integer status,
                           @Param("startTime") String startTime, @Param("endTime") String endTime);
}
