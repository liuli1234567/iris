package com.zhongke.service.impl;

import com.zhongke.mapper.MemberCardMapper;
import com.zhongke.pojo.MemberCard;
import com.zhongke.service.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MemberCardServiceImpl
 * @Description 商户会员卡
 * @Author liuli
 * @Date 2020/4/7 10:58
 * @Version 1.0
 **/
@Service
public class MemberCardServiceImpl implements MemberCardService {

    @Autowired(required = false)
    private MemberCardMapper memberCardMapper;

    @Override
    public List<MemberCard> find() {
        return memberCardMapper.selectAll();
    }

    @Override
    public void add(MemberCard memberCard) {
        memberCardMapper.insert(memberCard);
    }

    @Override
    public void update(MemberCard memberCard) {
        memberCardMapper.updateByPrimaryKey(memberCard);
    }
}
