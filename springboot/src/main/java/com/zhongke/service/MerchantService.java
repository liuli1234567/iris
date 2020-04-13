package com.zhongke.service;

import com.zhongke.pojo.Merchant;
import com.zhongke.pojo.Store;

import java.util.Date;
import java.util.Map;

public interface MerchantService {

    /**
     * @Description 根据商户id和时间段，查询首页数据
     * @author liuli
     * @date 2020/4/1 18:18
     * @param merchantId 商户id
     * @param startTime
     * @param endTime
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String,Object> getHomeByMerchantId(Integer merchantId, String startTime, String endTime);

    /**
     * @Description 查询商户拥有的订单信息
     * @author liuli
     * @date 2020/4/13 18:17
     * @param merchantId
     * @return com.zhongke.pojo.Store
     **/
    Store findByMerchantId(int merchantId);


}

