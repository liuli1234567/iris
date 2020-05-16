package com.zhongke.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.SpuMapper;
import com.zhongke.pojo.Spu;
import com.zhongke.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName SpuServiceImpl
 * @Description 商品
 * @Author liuli
 * @Date 2020/4/9 10:33
 * @Version 1.0
 **/
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired(required = false)
    private SpuMapper spuMapper;

    @Override
    public PageInfo<Spu> spus(String nameOrNo, int is_marketable, int page, int size) {
        List<Spu> spus = spuMapper.findByNameOrNo(nameOrNo,is_marketable);
        return new PageInfo<>(spus);
    }


    @Override
    public void add(Spu spu) {
        spuMapper.insertSelective(spu);
    }

    @Override
    public void delete(int id) {
        spuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public void batch(String spuIds,int status) {
        if (!StringUtils.isEmpty(spuIds)){
            String[] split = spuIds.split(",");
            for (String s : split) {
                if (1==status || -1==status){
                    Spu spu = new Spu();
                    spu.setId(Long.parseLong(s));
                    spu.setIsMarketable(status);
                    spuMapper.updateByPrimaryKeySelective(spu);
                }else {
                    Spu spu = new Spu();
                    spu.setId(Long.parseLong(s));
                    spuMapper.deleteByPrimaryKey(spu);
                }
            }
        }
    }

    @Override
    @Transactional
    public void addAll(List<Spu> spus) {
        if (spus != null && spus.size() > 0) {
            for (Spu spu : spus) {
                spuMapper.insertSelective(spu);
            }
        }
    }
}
