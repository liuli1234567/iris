package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.StoreMapper;
import com.zhongke.pojo.Store;
import com.zhongke.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName StoreServiceImpl
 * @Description 门店
 * @Author liuli
 * @Date 2020/4/1 18:43
 * @Version 1.0
 **/
@Service
public class StoreServiceImpl implements StoreService{

    @Autowired(required = false)
    private StoreMapper storeMapper;

    @Override
    public PageInfo<Store> stores(Store store, int page, int size) {
        PageHelper.startPage(page,size);
        List<Store> stores = storeMapper.selectByExample(store);
        return new PageInfo<>(stores);
    }

    @Override
    public void add(Store store) {
        storeMapper.insertSelective(store);
    }

    /**
     * @Description 修改门店信息
     * @author liuli
     * @date 2020/4/8 15:53
     * @param store
     * @return void
     **/
    @Override
    public void update(Store store) {
        storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public Store findById(int id) {
        return storeMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description 构建查询条件
     * @author liuli
     * @date 2020/4/8 15:48
     * @param store
     * @return tk.mybatis.mapper.entity.Example
     **/
    public Example createExample(Store store){
        Example example = new Example(Store.class);
        Example.Criteria criteria = example.createCriteria();
        if (store!=null){
            // 门店id
            if (!StringUtils.isEmpty(store.getId())){
                criteria.andEqualTo("id",store.getId());
            }
            // 门店名称
            if (!StringUtils.isEmpty(store.getName())){
                criteria.andEqualTo("name",store.getName());
            }
            // 状态
            if (!StringUtils.isEmpty(store.getStatus())){
                criteria.andEqualTo("status",store.getStatus());
            }
            // 门店电话
            if (!StringUtils.isEmpty(store.getTel())){
                criteria.andEqualTo("tel",store.getTel());
            }
            // 用户所属商户id
            if (!StringUtils.isEmpty(store.getMerchantId())){
                criteria.andEqualTo("merchantId",store.getMerchantId());
            }
        }
        return example;
    }
}
