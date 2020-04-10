package com.zhongke.service.impl;

import com.zhongke.mapper.OrderDetailsMapper;
import com.zhongke.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderDetailsServiceImpl
 * @Description 订单详情
 * @Author liuli
 * @Date 2020/4/9 9:49
 * @Version 1.0
 **/
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired(required = false)
    private OrderDetailsMapper orderDetailsMapper;
}
