package com.zhongke.service.impl;

import com.zhongke.mapper.MembpaySettingMapper;
import com.zhongke.pojo.MembpaySetting;
import com.zhongke.service.MembpaySettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MembpaySettingServiceImpl
 * @Description 会员支付设置
 * @Author liuli
 * @Date 2020/4/7 14:53
 * @Version 1.0
 **/
@Service
public class MembpaySettingServiceImpl implements MembpaySettingService {

    @Autowired(required = false)
    private MembpaySettingMapper membpaySettingMapper;

    @Override
    public MembpaySetting find() {
        return membpaySettingMapper.selectAll().get(0);
    }

    @Override
    public void update(MembpaySetting membpaySetting) {
        membpaySettingMapper.updateByPrimaryKey(membpaySetting);
    }

}
