package com.zhongke.task;

import com.zhongke.entity.DateUtil;
import com.zhongke.mapper.MerchantTransactionMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.MerchantTransaction;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CreateMerchantOrderCountTable
 * @Description 定时每天凌晨12点1分更新商户流水统计表数据
 * @Author liuli
 * @Date 2020/5/8 9:51
 * @Version 1.0
 **/
@Configuration
public class CreateMerchantOrderCountTable {
    private final Logger log = LoggerFactory.getLogger(CreateMerchantOrderCountTable.class);

    @Value("${mysql.table.orderNewName}")
    private String orderTableNewName;

    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private MerchantTransactionMapper merchantTransactionMapper;
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 1 0 * * ?")
    public void createMerchantTransactionTable(){
        List<Integer> merchantIds = new ArrayList<>();
        Collections.addAll(merchantIds,2,3,1,4,5,6,7);
        String startTime = DateUtil.getBeforeDate(-1)+ " 00:00:00";
        String endTime = DateUtil.getBeforeDate(-1)+ " 00:00:00";
        String orderTableName = (String) redisTemplate.boundValueOps(orderTableNewName).get();
        if (StringUtils.isEmpty(orderTableName)) {
            orderTableName = "zk_order";
        }
        for (Integer merchantId : merchantIds) {
            BigDecimal total_amount = orderMapper.totalAmountByMerchantId(orderTableName,merchantId,startTime,endTime);//订单总金额
            BigDecimal refund_amount = orderMapper.refundAmountByMerchantId(orderTableName,merchantId,startTime,endTime);//退款总金额
            BigDecimal cancel_amount = orderMapper.cancelAmountByMerchantId(orderTableName,merchantId,startTime,endTime);
            BigDecimal received_amount = total_amount.subtract(refund_amount.add(cancel_amount));//实收总金额
            int transaction_number = orderMapper.transactionNumberByMerchantId(orderTableName,merchantId,startTime,endTime);//交易笔数
            int refund_number = orderMapper.refundNumberByMerchantId(orderTableName,merchantId,startTime,endTime);//退款笔数

            MerchantTransaction merchantTransaction = new MerchantTransaction();
            merchantTransaction.setMerchantId(merchantId);
            merchantTransaction.setTime(DateUtil.getBeforeDate(-1));
            merchantTransaction.setTotalAmount(total_amount);
            merchantTransaction.setRefundAmount(refund_amount);
            merchantTransaction.setReceivedAmount(received_amount);
            merchantTransaction.setTransactionNumber(transaction_number);
            merchantTransaction.setRefundNumber(refund_number);
            merchantTransaction.setCreateTime(new Date());
            merchantTransactionMapper.insertSelective(merchantTransaction);
        }
        log.info("定时每天凌晨12点1分更新商户流水统计表数据成功");
    }
}
