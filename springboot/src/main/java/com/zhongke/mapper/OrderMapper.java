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

    Integer findDeviceIdByOutTradeNo(String outTradeNo);

    /**
     * @Description 订单总数
     * @author liuli
     * @date 2020/4/6 16:07
     * @param order
     * @return int
     **/
    Integer findOrderNum(Order order);

    /**
     * @Description 订单总金额
     * @author liuli
     * @date 2020/4/6 16:07
     * @param order
     * @return int
     **/
    Integer orderMoney(Order order);

    /**
     * @Description 退款总金额
     * @author liuli
     * @date 2020/4/6 16:08
     * @param order
     * @return int
     **/
    Integer refundMoney(Order order);

    /**
     * @Description 顾客实付
     * @author liuli
     * @date 2020/4/6 16:08
     * @param order
     * @return int
     **/
    Integer realMoney(Order order);

    /**
     * @Description 优惠
     * @author liuli
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

    List<Order> findOrdersByDeviceId(int deviceId);

    /**
     * @Description 商户实收总金额
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal merchantPaidMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 商户实退总金额
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal retreatMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 顾客实付金额
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal actuallyPaidMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 支付成功订单总数
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.lang.Integer
     **/
    Integer orderTotal(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 支付成功订单总金额
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal orderTotalMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 商家优惠总金额
     * @author liuli
     * @date 2020/4/14 10:25
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal merchantDiscount(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 其他方优惠总金额
     * @author liuli
     * @date 2020/4/14 10:26
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **//*
    BigDecimal otherDiscount(String payStartTime, String payEndTime,int deviceId);*/

    /**
     * @Description 退款订单总数
     * @author liuli
     * @date 2020/4/14 10:26
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.lang.Integer
     **/
    Integer refundOrderCount(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 退款订单总金额
     * @author liuli
     * @date 2020/4/14 10:26
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal refundOrderMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 有效订单数量
     * @author liuli
     * @date 2020/4/14 14:43
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.lang.Integer
     **/
    Integer effectiveOrderNum(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 有效订单总金额
     * @author liuli
     * @date 2020/4/14 14:44
     * @param payStartTime
     * @param payEndTime
     * @param deviceId
     * @return java.math.BigDecimal
     **/
    BigDecimal effectiveOrderMoney(@Param("payStartTime") String payStartTime, @Param("payEndTime") String payEndTime,@Param("deviceId") int deviceId);

    /**
     * @Description 根据门店id和订单信息查询订单列表
     * @author liuli
     * @date 2020/4/16 18:27
     * @param order
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    List<Order> findOrdersByStoreId(Order order);

    /**
     * @Description 通过订单号查找订单
     * @author liuli
     * @date 2020/4/26 17:25
     * @param out_trade_no
     * @return com.zhongke.pojo.Order
     **/
    Order findOrderByOrderId(String out_trade_no);
}
