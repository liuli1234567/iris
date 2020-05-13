package com.zhongke.mapper;

import com.zhongke.pojo.MerchantTransaction;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

public interface MerchantTransactionMapper extends Mapper<MerchantTransaction> {
    BigDecimal beforeTotal_amount(@Param("merchantTd") Integer merchantId, @Param("startTime") String payStartTime, @Param("emdTime") String payEndTime);

    BigDecimal beforeRefund_amount(@Param("merchantId") Integer merchantId, @Param("startTime") String payStartTime, @Param("endTime") String payEndTime);

    BigDecimal beforeMerchant_amount(@Param("merchantId") Integer merchantId, @Param("startTime") String payStartTime, @Param("endTime") String payEndTime);

    BigDecimal beforeOtherDiscount(@Param("merchantId") Integer merchantId, @Param("startTime") String payStartTime, @Param("endTime") String payEndTime);

    int beforeOrderCount(@Param("merchantId") Integer merchantId, @Param("startTime") String payStartTime, @Param("endTime") String payEndTime);

    int beforeRefundCount(@Param("merchantId") Integer merchantId, @Param("startTime") String payStartTime, @Param("endTime") String payEndTime);
}
