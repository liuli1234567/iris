package com.zhongke.mapper;

import com.zhongke.pojo.OrderDetails;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderDetailsMapper extends Mapper<OrderDetails> {
    List<OrderDetails> orderDetails(String orderId);

}
