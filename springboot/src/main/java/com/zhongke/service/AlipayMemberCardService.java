package com.zhongke.service;

import com.zhongke.pojo.AlipayMemberCard;

public interface AlipayMemberCardService {
    AlipayMemberCard find();

    void add(AlipayMemberCard alipayMemberCard);
}
