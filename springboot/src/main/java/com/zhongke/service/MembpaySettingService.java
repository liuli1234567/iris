package com.zhongke.service;

import com.zhongke.pojo.MembpaySetting;

import java.util.List;

public interface MembpaySettingService {
    List<MembpaySetting> find();

    void update(MembpaySetting membpaySetting);
}
