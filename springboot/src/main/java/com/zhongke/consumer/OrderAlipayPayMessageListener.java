package com.zhongke.consumer;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradePayResponse;
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
 * @ClassName OrderAlipayPayMessageListener
 * @Description 支付宝支付消息监听
 * @Author liuli
 * @Date 2020/4/29 10:43
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = {"${mq.pay.queue.alipayorder}"})
public class OrderAlipayPayMessageListener {

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
        AlipayTradePayResponse response = (AlipayTradePayResponse) map.get("response");
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println(response.getBody());
            List<Order> orders = findTradeNo(response.getTradeNo(),response.getOutTradeNo());
            for (Order order : orders) {
                System.out.println(order);
            }
            if (orders.size() == 0) { // 查询数据库是否已存在这条流水，不存在就插入
                //保存订单数据
                Order order = new Order();
                order.setOrderId(response.getOutTradeNo());// 订单号
                order.setOrderAmount(new BigDecimal(response.getTotalAmount()));// 订单金额
                order.setBuyerLogonId(response.getBuyerLogonId());// 买家支付宝账号
                order.setBuyerUserId(response.getBuyerUserId()); // 买家userID
                order.setStatus(1); // 设置支付状态为：已支付
                order.setCode(response.getCode()); // 支付状态码
                order.setMsg(response.getMsg()); // 支付描述
                order.setSubCode(response.getSubCode()); // 支付错误码
                order.setSubMsg(response.getSubMsg()); // 支付错误描述
                order.setPayMethod("支付宝支付"); // 支付方式
                order.setPayAisle("支付宝"); // 支付通道
                order.setActuallyPaid(new BigDecimal(response.getTotalAmount())); // 实付金额
                order.setDiscount(new BigDecimal(response.getDiscountAmount() == null ? "0" : response.getDiscountAmount()).add(new BigDecimal(response.getMdiscountAmount() == null ? "0" : response.getMdiscountAmount()))); // 优惠金额（商户优惠金额+平台优惠金额）
                order.setTransactionId(response.getTradeNo()); // 交易流水号
                order.setFundChannel(response.getFundBillList() == null ? null : response.getFundBillList().get(0).getFundChannel()); // 支付渠道
                order.setDeviceId(deviceMapper.findDeviceIdByDeviceNo(map.get("device_no").toString()) == null ? 0 : deviceMapper.findDeviceIdByDeviceNo(map.get("device_no").toString())); // 设备id
                Device device = new Device();
                device.setDeviceNo((String)map.get("device_no"));
                Device device1 = deviceMapper.selectOne(device);
                if (device1 != null) {
                    Integer storeId = device1.getStoreId();
                    Store store = new Store();
                    store.setId(storeId);
                    Store store1 = storeMapper.selectByPrimaryKey(store);
                    if (store1 != null) {
                        order.setMerchantId(store1.getMerchantId());
                    }
                }
                order.setCashierId(1); // 收银员id
                order.setStoreName(response.getStoreName()); // 门店名称
                order.setUpdatetime(new Date()); // 更新时间
                order.setCreateTime(new Date()); // 创建时间
                order.setPayTime(response.getGmtPayment()); // 支付时间
                String orderTableName = (String) redisTemplate.boundValueOps(orderTableNewName).get();
                if (!StringUtils.isEmpty(orderTableName)){
                    order.setTableNewName(orderTableName);
                    orderMapper.saveOrderToNewTableName(order);
                }else {
                    orderMapper.insertSelective(order);
                }
            }
        } else {
            System.out.println("调用失败");
            System.out.println(response.getBody());
            List<Order> orders = findTradeNo(response.getTradeNo(),response.getOutTradeNo());
            for (Order order : orders) {
                System.out.println(order);
            }
            if (orders.size() == 0) {
                //保存订单数据
                Order order = new Order();
                order.setOrderId(response.getOutTradeNo());// 订单号
                order.setOrderAmount(new BigDecimal(response.getTotalAmount()==null?"0":response.getTotalAmount()));// 订单金额
                order.setBuyerLogonId(response.getBuyerLogonId());// 买家支付宝账号
                order.setBuyerUserId(response.getBuyerUserId()); // 买家userID
                order.setStatus(-3); // 设置支付状态为：支付失败
                order.setCode(response.getCode()); // 支付状态码
                order.setMsg(response.getMsg()); // 支付描述
                order.setSubCode(response.getSubCode()); // 支付错误码
                order.setSubMsg(response.getSubMsg()); // 支付错误描述
                order.setPayMethod("支付宝支付"); // 支付方式
                order.setPayAisle("支付宝"); // 支付通道
                order.setTransactionId(response.getTradeNo()); // 交易流水号
                order.setDeviceId(deviceMapper.findDeviceIdByDeviceNo(map.get("device_no").toString()) == null ? 0 : deviceMapper.findDeviceIdByDeviceNo(map.get("device_no").toString())); // 设备id
                Device device = new Device();
                device.setDeviceNo((String)map.get("device_no"));
                Device device1 = deviceMapper.selectOne(device);
                if (device1 != null) {
                    Integer storeId = device1.getStoreId();
                    Store store = new Store();
                    store.setId(storeId);
                    Store store1 = storeMapper.selectByPrimaryKey(store);
                    if (store1 != null) {
                        order.setMerchantId(store1.getMerchantId());
                    }
                }
                order.setCashierId(1); // 收银员id
                order.setStoreName(response.getStoreName()); // 门店名称
                order.setUpdatetime(new Date()); // 更新时间
                order.setCreateTime(new Date()); // 创建时间
                order.setPayTime(response.getGmtPayment()); // 支付时间
                String orderTableName = (String) redisTemplate.boundValueOps(orderTableNewName).get();
                if (!StringUtils.isEmpty(orderTableName)){
                    order.setTableNewName(orderTableName);
                    orderMapper.saveOrderToNewTableName(order);
                }else {
                    orderMapper.insertSelective(order);
                }
            }
        }
    }

    /**
     * @Description 根据交易流水号查数据库是否已存在
     * @author liuli
     * @date 2020/4/26 12:17
     * @param tradeNo
     * @return java.util.List<com.zhongke.pojo.Order>
     **/
    private List<Order> findTradeNo(String tradeNo,String order_id){
        Order order = new Order();
        order.setTransactionId(tradeNo);
        order.setOrderId(order_id);
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("transactionId",order.getTransactionId());
        criteria.andEqualTo("orderId",order.getOrderId());
        return orderMapper.selectByExample(example);
    }

}
