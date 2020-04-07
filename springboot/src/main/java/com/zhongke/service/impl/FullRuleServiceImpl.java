package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.FullRuleMapper;
import com.zhongke.pojo.FullRule;
import com.zhongke.service.FullRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FullRuleServiceImpl
 * @Description 满减规则
 * @Author liuli
 * @Date 2020/4/7 11:58
 * @Version 1.0
 **/
@Service
public class FullRuleServiceImpl implements FullRuleService {

    @Autowired(required = false)
    private FullRuleMapper fullRuleMapper;

    @Override
    public PageInfo<FullRule> fullRules(String startTime, String endTime,String activityStatus, int status, int page, int size) {
        List<FullRule> fullRules = fullRuleMapper.fullRules(startTime,endTime,status);
        if (fullRules != null && fullRules.size()>0) {
            Date date = new Date();
            for (FullRule fullRule : fullRules) {
                if (date.getTime()-fullRule.getStartTime().getTime()>0){
                    if (date.getTime()-fullRule.getEndTime().getTime()<0){
                        fullRule.setActivityStatus("进行中");
                    }else {
                        fullRule.setActivityStatus("已结束");
                    }
                }else {
                    fullRule.setActivityStatus("未开始");
                }
            }
            if (!StringUtils.isEmpty(activityStatus)){
                for (int i = 0; i < fullRules.size(); i++) {
                    if (!activityStatus.equals(fullRules.get(i).getActivityStatus())){
                        fullRules.remove(i);
                        i--;
                    }
                }
            }
        }
        PageHelper.startPage(page,size);
        return new PageInfo<>(fullRules);
    }

    @Override
    public void add(FullRule fullRule) {
        fullRuleMapper.insert(fullRule);
    }

    @Override
    public void update(FullRule fullRule) {
        fullRuleMapper.updateByPrimaryKey(fullRule);
    }
}
