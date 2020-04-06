package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.IntegralRecordMapper;
import com.zhongke.pojo.IntegralRecord;
import com.zhongke.pojo.Member;
import com.zhongke.service.IntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName IntegralRecordServiceImpl
 * @Description 积分记录
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/5
 * @Version 2.1
 **/
@Service
public class IntegralRecordServiceImpl implements IntegralRecordService {

    @Autowired(required = false)
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public PageInfo<IntegralRecord> integralRcs(IntegralRecord integralRecord, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = new Example(IntegralRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (integralRecord != null) {
            if (!StringUtils.isEmpty(integralRecord.getType())){
                criteria.andEqualTo("type",integralRecord.getType());
            }
            if (!StringUtils.isEmpty(integralRecord.getStartTime())){
                if (!StringUtils.isEmpty(integralRecord.getEndTime())){
                    criteria.andBetween("createTime",integralRecord.getStartTime()+" 00:00:00",integralRecord.getEndTime()+" 23:59:59");
                }else {
                    criteria.andGreaterThanOrEqualTo("createTime",integralRecord.getStartTime()+" 00:00:00");
                }
            }else {
                if (!StringUtils.isEmpty(integralRecord.getEndTime())){
                    criteria.andLessThanOrEqualTo("createTime",integralRecord.getEndTime()+" 23:59:59");
                }
            }
        }
        return new PageInfo<>(integralRecordMapper.selectByExample(example));
    }
}