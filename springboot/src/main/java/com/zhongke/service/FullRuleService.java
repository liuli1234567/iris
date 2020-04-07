package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.FullRule;

public interface FullRuleService {
    /**
     * @Description
     * @author liuli
     * @date 2020/4/7 14:24
     * @param startTime
     * @param endTime
     * @param activityStatus 活动状态
     * @param status
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.FullRule>
     **/
    PageInfo<FullRule> fullRules(String startTime,String endTime,String activityStatus,int status, int page, int size);

    void add(FullRule fullRule);

    void update(FullRule fullRule);
}
