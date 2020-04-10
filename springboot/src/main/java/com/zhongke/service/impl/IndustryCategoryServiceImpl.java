package com.zhongke.service.impl;

import com.zhongke.mapper.IndustryCategoryMapper;
import com.zhongke.pojo.IndustryCategory;
import com.zhongke.service.IndustryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName IndustryCategoryServiceImpl
 * @Description 行业列表
 * @Author liuli
 * @Date 2020/4/8 16:21
 * @Version 1.0
 **/
@Service
public class IndustryCategoryServiceImpl implements IndustryCategoryService {

    @Autowired(required = false)
    private IndustryCategoryMapper industryCategoryMapper;

    @Override
    public List<IndustryCategory> industrys() {
        return industryCategoryMapper.selectAll();
    }
}
