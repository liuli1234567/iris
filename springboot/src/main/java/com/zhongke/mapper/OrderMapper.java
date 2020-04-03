package com.zhongke.mapper;

import com.zhongke.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    List<Order> findByDeviceIdAndTime(@Param("id") Integer id, @Param("startTime") String startTime, @Param("endTime") String endTime);

}
