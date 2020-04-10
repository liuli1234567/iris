package com.zhongke.service;

import com.zhongke.pojo.AlipayMemberCard;

import java.util.List;

public interface AlipayMemberCardService {
    List<AlipayMemberCard> find();

    void add(AlipayMemberCard alipayMemberCard);
}
