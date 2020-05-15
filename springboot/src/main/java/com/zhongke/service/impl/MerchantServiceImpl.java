package com.zhongke.service.impl;

import com.zhongke.mapper.*;
import com.zhongke.pojo.*;
import com.zhongke.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MerchantServiceImpl
 * @Description 商户
 * @Author liuli
 * @Date 2020/4/1 18:19
 * @Version 1.0
 **/
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired(required = false)
    private StoreMapper storeMapper;
    @Autowired(required = false)
    private DeviceMapper deviceMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private MerchantMapper merchantMapper;
    @Autowired(required = false)
    private AnnouncementMapper announcementMapper;

    /**
     * @Description 根据商户id和时间段，查询首页数据
     * @author liuli
     * @date 2020/4/1 18:18
     * @param merchantId 商户id
     * @param startTime
     * @param endTime
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getHomeByMerchantId(Integer merchantId, String startTime, String endTime) {
        if (merchantId != null) {
            HashMap<String, Object> map = new HashMap<>();
            Announcement annou = announcementMapper.findOneAnnouByTime(merchantId);
            map.put("announcement",annou); // 商户下的最新公告
            Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
            map.put("merchantName",merchant.getName()); // 商户名称
            map.put("payeeName",merchant.getPayeeName()); // 商户收款人姓名
            map.put("merchantNumber",merchant.getNumber()); // 商户号
            map.put("payeeBank",merchant.getPayeeBank()); // 收款银行卡号
            BigDecimal totalMoney = new BigDecimal("0.0");
            int transactionNum = 0; // 交易笔数
            BigDecimal refundMoney = new BigDecimal("0.0"); // 退款金额
            int refundNum = 0; // 退款笔数
            int alipayNum = 0; // 支付宝交易笔数
            int weChatNum = 0; // 微信交易笔数
            int otherNum = 0; // 其他交易笔数
            Store store = new Store();
            store.setMerchantId(merchantId);
            List<Store> stores = storeMapper.select(store);
            if (stores != null && stores.size()>0) {
                for (Store store1 : stores) {
                    String name = store1.getName();
                    Device device = new Device();
                    device.setStoreName(name);
                    List<Device> devices = deviceMapper.select(device);
                    if (devices != null && devices.size()>0) {
                        for (Device device1 : devices) {
                            List<Order> orders = orderMapper.findByDeviceIdAndTime(device1.getId(),startTime,endTime);
                            if (orders != null && orders.size()>0) {
                                for (Order order : orders) {
                                    totalMoney = order.getOrderAmount().add(totalMoney);
                                    if (order.getStatus() != null && order.getStatus() == -2){
                                        refundMoney = order.getOrderAmount().add(refundMoney);
                                        refundNum++;
                                    }
                                    if (!StringUtils.isEmpty(order.getPayMethod()) && "支付宝".equals(order.getPayMethod())){
                                        alipayNum++;
                                    }
                                    if (!StringUtils.isEmpty(order.getPayMethod()) && "微信".equals(order.getPayMethod())){
                                        weChatNum++;
                                    }
                                }
                                transactionNum += orders.size();
                            }
                        }
                    }
                }
            }
            if (transactionNum != 0){
                otherNum = transactionNum -alipayNum-weChatNum;
            }
            map.put("totalMoney",totalMoney); // 交易总金额
            map.put("transactionNum",transactionNum); // 交易笔数
            map.put("refundMoney",refundMoney); // 退款总金额
            map.put("refundNum",refundNum); // 退款笔数
            map.put("revenueMoney",totalMoney.subtract(refundMoney)); // 实际营收总金额
            map.put("alipayNum",alipayNum); // 支付宝支付交易笔数
            map.put("weChatNum",weChatNum); // 微信支付交易笔数
            map.put("otherNum",otherNum); // 其他支付交易笔数
            return map;
        }
        return null;
    }

    @Override
    public Store findByMerchantId(int merchantId) {
        return null;
    }
}
