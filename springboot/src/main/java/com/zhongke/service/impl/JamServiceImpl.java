package com.zhongke.service.impl;

import com.zhongke.service.JamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName JamServiceImpl
 * @Description 卡卷
 * @Author liuli
 * @Date 2020/4/3 18:40
 * @Version 1.0
 **/
@Service
public class JamServiceImpl implements JamService {

    @Autowired(required = false)
    private JamService jamService;

}
