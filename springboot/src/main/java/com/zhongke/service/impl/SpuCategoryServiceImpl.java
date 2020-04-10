package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.SpuCategoryMapper;
import com.zhongke.pojo.SpuCategory;
import com.zhongke.service.SpuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SpuCategoryServiceImpl
 * @Description 商品分类
 * @Author liuli
 * @Date 2020/4/9 11:57
 * @Version 1.0
 **/
@Service
public class SpuCategoryServiceImpl implements SpuCategoryService {

    @Autowired(required = false)
    private SpuCategoryMapper spuCategoryMapper;

    @Override
    public PageInfo<SpuCategory> spuCategorys(int page, int size) {
        PageHelper.startPage(page,size);
        List<SpuCategory> spuCategories = spuCategoryMapper.selectAll();
        return new PageInfo<>(spuCategories);
    }

    @Override
    public void add(SpuCategory spuCategory) {
        spuCategoryMapper.insertSelective(spuCategory);
    }

    @Override
    public void delete(int id) {
        spuCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SpuCategory spuCategory) {
        spuCategoryMapper.updateByPrimaryKeySelective(spuCategory);
    }
}
