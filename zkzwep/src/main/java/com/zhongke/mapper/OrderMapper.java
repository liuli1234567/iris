package com.zhongke.mapper;

import com.zhongke.pojo.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    /**
     * @Description 订单列表查询
     * @author liuli
     * @date 2020/5/21 16:43
     * @param nameOrPhone 客户姓名或手机号
     * @param status 订单状态
     * @param startTime 订单创建时间起始时间
     * @param endTime 订单创建时间结束时间
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Order>
     **/
    List<Order> findAll(@Param("nameOrPhone") String nameOrPhone, @Param("status") Integer status, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * @Description 通过公众号用户openid查询订单列表
     * @author liuli
     * @date 2020/5/26 18:32
     * @param openid
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    List<Order> findByOpenid(String openid);
}
