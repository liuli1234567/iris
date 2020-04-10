package com.zhongke.service.impl;

import com.zhongke.mapper.AlipayMemberCardMapper;
import com.zhongke.pojo.AlipayMemberCard;
import com.zhongke.service.AlipayMemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AlipayMemberCardServiceImpl
 * @Description 支付宝会员卡
 * @Author liuli
 * @Date 2020/4/7 11:36
 * @Version 1.0
 **/
@Service
public class AlipayMemberCardServiceImpl implements AlipayMemberCardService {

    @Autowired(required = false)
    private AlipayMemberCardMapper alipayMemberCardMapper;

    @Override
    public List<AlipayMemberCard> find() {
        return alipayMemberCardMapper.selectAll();
    }

    @Override
    public void add(AlipayMemberCard alipayMemberCard) {
        alipayMemberCardMapper.insert(alipayMemberCard);
    }
}
