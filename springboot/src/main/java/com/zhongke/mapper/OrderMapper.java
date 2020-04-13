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

    /**
     * @Description 查询商品订单列表
     * @author liuli
     * @date 2020/4/7 16:04
     * @param storeOrOrder 门店名称或订单号
     * @param startTime
     * @param endTime
     * @param payMethod 支付方式
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Order>
     **/
    List<Order> findSpuOrders(@Param("storeOrOrder") String storeOrOrder, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("payMethod") String payMethod);

    Order findOne(String orderId);

    /**
     * @Description
     * @author liuli
     * @date 2020/4/13 15:55
     * @param payStartTime
     * @param payEndTime
     * @return java.math.BigDecimal
     **/
    BigDecimal merchantPaidMoney(String payStartTime, String payEndTime);

    BigDecimal retreatMoney(String payStartTime, String payEndTime);

    BigDecimal actuallyPaidMoney(String payStartTime, String payEndTime);

    Integer orderTotal(String payStartTime, String payEndTime);

    BigDecimal orderTotalMoney(String payStartTime, String payEndTime);

    BigDecimal merchantDiscount(String payStartTime, String payEndTime);

    BigDecimal otherDiscount(String payStartTime, String payEndTime);

    Integer refundOrderCount(String payStartTime, String payEndTime);

    BigDecimal refundOrderMoney(String payStartTime, String payEndTime);
}
