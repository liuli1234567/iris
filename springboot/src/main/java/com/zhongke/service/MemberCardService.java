package com.zhongke.service;

import com.zhongke.pojo.MemberCard;

import java.util.List;

public interface MemberCardService {
    List<MemberCard> find();

    void add(MemberCard memberCard);

    void update(MemberCard memberCard);

}
