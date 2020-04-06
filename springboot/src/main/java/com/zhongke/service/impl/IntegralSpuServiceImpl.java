package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.IntegralSpuMapper;
import com.zhongke.pojo.IntegralSpu;
import com.zhongke.pojo.Jam;
import com.zhongke.service.IntegralSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ClassName IntegralSpuServiceImpl
 * @Description 积分商品
 * @Author liuli
 * @CreateDate 2020/4/6
 * @Version 2.1
 **/
@Service
public class IntegralSpuServiceImpl implements IntegralSpuService {

    @Autowired(required = false)
    private IntegralSpuMapper integralSpuMapper;

    @Override
    public PageInfo<IntegralSpu> spus(IntegralSpu integralSpu, int page, int size) {
        Example example = new Example(IntegralSpu.class);
        Example.Criteria criteria = example.createCriteria();
        if (integralSpu != null) {
            if (!StringUtils.isEmpty(integralSpu.getSpuName())) {
                criteria.andEqualTo(integralSpu.getSpuName());
            }
        }
        List<IntegralSpu> spus = integralSpuMapper.selectByExample(example);
        Date date = new Date();
        if (spus != null && spus.size()>0) {
            for (IntegralSpu spu : spus) {
                if (date.getTime()-spu.getStartTime().getTime()>0){
                    if (date.getTime()-spu.getEndTime().getTime()<0){
                        spu.setStatus(1);
                    }else {
                        spu.setStatus(-1);
                    }
                }else {
                    spu.setStatus(2);
                }
            }
            if (integralSpu != null && integralSpu.getStatus() !=null) {
                int status = integralSpu.getStatus();
                for (int i = 0; i < spus.size(); i++) {
                    if (status != spus.get(i).getStatus()){
                        spus.remove(i);
                        i--;
                    }
                }
            }
        }
        PageHelper.startPage(page,size);
        return new PageInfo<>(spus);
    }

    @Override
    public void add(IntegralSpu integralSpu) {
        integralSpuMapper.insert(integralSpu);
    }
}