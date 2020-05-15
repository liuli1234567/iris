package com.zhongke.consumer;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.zhongke.mapper.DeviceMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.mapper.StoreMapper;
import com.zhongke.pojo.Device;
import com.zhongke.pojo.Order;
import com.zhongke.pojo.Store;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderAliRefundPayMessageListener
 * @Description 支付宝退款消息监听
 * @Author liuli
 * @Date 2020/4/29 10:43
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = {"${mq.pay.queue.alirefundorder}"})
public class OrderAliRefundPayMessageListener {

    @Value("${mysql.table.orderNewName}")
    private String orderTableNewName;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private DeviceMapper deviceMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private StoreMapper storeMapper;

    @RabbitHandler
    public void readMessage(Map map) {
        // 根据response中的结果继续业务逻辑处理
        System.out.println(map);
        AlipayTradeRefundResponse response = (AlipayTradeRefundResponse) map.get("response");
        System.out.println(response.isSuccess());
        if (response.isSuccess()) {
            //保存订单数据
            saveOrder(map.get("device_no").toString(),response);
        } else {
            //保存订单数据
            saveOrder(map.get("device_no").toString(),response);
        }
    }

    private void saveOrder(String device_no, AlipayTradeRefundResponse response) {
        //保存订单数据
        Order order = new Order();
        order.setOrderId(response.getOutTradeNo());// 订单号
        order.setOrderAmount(new BigDecimal(response.getRefundFee()));// 退款总金额
        order.setBuyerLogonId(response.getBuyerLogonId());// 买家支付宝账号
        order.setBuyerUserId(response.getBuyerUserId()); // 买家userID
        order.setStatus(-2); // 设置支付状态为：退款
        order.setCode(response.getCode()); // 状态码
        order.setMsg(response.getMsg()); // 描述
        order.setSubCode(response.getSubCode()); // 错误码
        order.setSubMsg(response.getSubMsg()); // 错误描述
        order.setPayMethod("支付宝支付"); // 支付方式
        order.setPayAisle("支付宝"); // 支付通道
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundBuyerAmount() == null ? "0" : response.getPresentRefundBuyerAmount())); // 本次退款金额中买家退款金额
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundDiscountAmount() == null ? "0" : response.getPresentRefundDiscountAmount())); // 本次退款金额中平台优惠退款金额
        order.setRefundBuyerAmount(new BigDecimal(response.getPresentRefundMdiscountAmount() == null ? "0" : response.getPresentRefundMdiscountAmount())); // 本次退款金额中商家优惠退款金额
        order.setTransactionId(response.getTradeNo()); // 交易流水号
        order.setFundChannel(response.getRefundDetailItemList() == null ? null : response.getRefundDetailItemList().get(0).getFundChannel()); // 支付渠道
        order.setDeviceId(deviceMapper.findDeviceIdByDeviceNo(device_no)==null?0:deviceMapper.findDeviceIdByDeviceNo(device_no)); // 设备id
        Device device = new Device();
        device.setDeviceNo(device_no);
        Device device1 = deviceMapper.selectOne(device);
        if (device1 != null) {
            String storeName = device1.getStoreName();
            Store store = new Store();
            store.setName(storeName);
            Store store1 = storeMapper.selectByPrimaryKey(store);
            if (store1 != null) {
                order.setMerchantId(store1.getMerchantId());
            }
        }
        order.setCashierId(1); // 收银员id
        order.setStoreName(response.getStoreName()); // 门店名称
        order.setUpdatetime(new Date()); // 更新时间
        order.setCreateTime(new Date()); // 创建时间
        order.setPayTime(response.getGmtRefundPay()); // 退款时间
        String orderTableName = (String) redisTemplate.boundValueOps(orderTableNewName).get();
        if (!StringUtils.isEmpty(orderTableName)){
            order.setTableNewName(orderTableName);
            orderMapper.saveOrderToNewTableName(order);
        }else {
            orderMapper.insertSelective(order);
        }
    }
}
