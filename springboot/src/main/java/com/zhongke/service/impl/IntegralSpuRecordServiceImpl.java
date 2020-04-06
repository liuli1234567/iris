package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.IntegralSpuRecordMapper;
import com.zhongke.pojo.IntegralSpuRecord;
import com.zhongke.service.IntegralSpuRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName IntegralSpuRecordServiceImpl
 * @Description 积分商品兑换记录
 * @Author liuli
 * @CreateDate 2020/4/6
 * @Version 2.1
 **/
@Service
public class IntegralSpuRecordServiceImpl implements IntegralSpuRecordService {

    @Autowired(required = false)
    private IntegralSpuRecordMapper integralSpuRecordMapper;

    @Override
    public PageInfo<IntegralSpuRecord> record(IntegralSpuRecord integralSpuRecord, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = new Example(IntegralSpuRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (integralSpuRecord != null) {
            if (!StringUtils.isEmpty(integralSpuRecord.getTel())){
                criteria.andEqualTo("tel",integralSpuRecord.getTel());
            }
            if (!StringUtils.isEmpty(integralSpuRecord.getStartTime())){
                if (!StringUtils.isEmpty(integralSpuRecord.getEndTime())){
                    criteria.andBetween("exchangeTime",integralSpuRecord.getStartTime()+" 00:00:00",integralSpuRecord.getEndTime()+" 23:59:59");
                }else {
                    criteria.andGreaterThanOrEqualTo("exchangeTime",integralSpuRecord.getStartTime()+" 00:00:00");
                }
            }else {
                if (!StringUtils.isEmpty(integralSpuRecord.getEndTime())){
                    criteria.andLessThanOrEqualTo("exchangeTime",integralSpuRecord.getEndTime()+" 23:59:59");
                }
            }
        }
        List<IntegralSpuRecord> integralSpuRecords = integralSpuRecordMapper.selectByExample(example);
        return new PageInfo<>(integralSpuRecords);
    }
}