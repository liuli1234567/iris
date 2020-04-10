package com.zhongke.service;

import com.zhongke.pojo.IntegralRule;

import java.util.List;

public interface IntegralRuleService {
    List<IntegralRule> find();

    void update(IntegralRule integralRule);
}
