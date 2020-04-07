package com.zhongke.service;

import com.zhongke.pojo.MemberCard;

public interface MemberCardService {
    MemberCard find();

    void add(MemberCard memberCard);

    void update(MemberCard memberCard);

}
