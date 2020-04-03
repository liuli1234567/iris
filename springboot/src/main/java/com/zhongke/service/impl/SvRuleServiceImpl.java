package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.SvRuleMapper;
import com.zhongke.pojo.SvRule;
import com.zhongke.service.SvRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SvRuleServiceImpl
 * @Description 储值规则
 * @Author liuli
 * @Date 2020/4/3 11:25
 * @Version 1.0
 **/
@Service
public class SvRuleServiceImpl implements SvRuleService {

    @Autowired(required = false)
    private SvRuleMapper svRuleMapper;

    @Override
    public PageInfo<SvRule> svRules(int page, int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(svRuleMapper.selectAll());
    }

    @Override
    public void add(SvRule svRule) {
        svRuleMapper.insert(svRule);
    }

    @Override
    public void delete(int id) {
        svRuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SvRule svRule) {
        svRuleMapper.updateByPrimaryKey(svRule);
    }
}
