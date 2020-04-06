package com.zhongke.service.impl;

import com.zhongke.mapper.IntegralRuleMapper;
import com.zhongke.pojo.IntegralRule;
import com.zhongke.service.IntegralRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName IntegralRuleServiceImpl
 * @Description 积分规则
 * @Author liuli
 * @CreateDate 2020/4/6
 * @Version 2.1
 **/
@Service
public class IntegralRuleServiceImpl implements IntegralRuleService {

    @Autowired(required = false)
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public IntegralRule find() {
        return integralRuleMapper.selectAll().get(0);
    }

    @Override
    public void update(IntegralRule integralRule) {
        integralRuleMapper.updateByPrimaryKey(integralRule);
    }
}