package com.zhongke.mapper;

import com.zhongke.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    List<Order> findByDeviceIdAndTime(@Param("id") Integer id, @Param("startTime") String startTime, @Param("endTime") String endTime);

    BigDecimal findConsumByMemberId(Integer id);

    /**
     * @Description 订单总数
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 16:07
     * @param order
     * @return int
     **/
    Integer findOrderNum(Order order);

    /**
     * @Description 订单总金额
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 16:07
     * @param order
     * @return int
     **/
    Integer orderMoney(Order order);

    /**
     * @Description 退款总金额
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 16:08
     * @param order
     * @return int
     **/
    Integer refundMoney(Order order);

    /**
     * @Description 顾客实付
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 16:08
     * @param order
     * @return int
     **/
    Integer realMoney(Order order);

    /**
     * @Description 优惠
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 16:08
     * @param order
     * @return int
     **/
    Integer discountMoney(Order order);
}
