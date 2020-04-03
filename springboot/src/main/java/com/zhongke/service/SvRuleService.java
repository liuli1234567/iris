package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.SvRule;

public interface SvRuleService {

    PageInfo<SvRule> svRules(int page, int size);

    void add(SvRule svRule);

    void delete(int id);

    void update(SvRule svRule);
}
