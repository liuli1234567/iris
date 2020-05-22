package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.ProductMapper;
import com.zhongke.pojo.Product;
import com.zhongke.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:10
 * @Version 1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductMapper productMapper;

    /**
     * @Description 产品入库
     * @author liuli
     * @date 2020/5/21 10:25
     * @param productName 产品名称
     * @param stock_in  入库吨数
     * @return void
     **/
    @Override
    public void add(String productName, int stock_in) {
        Product p = new Product();
        p.setName(productName);
        Product produc = productMapper.selectOne(p);
        Product product = new Product();
        product.setName(productName);
        if (produc != null) {
            product.setStock(produc.getStock()+stock_in);
            product.setStockIn(produc.getStockIn()+stock_in); // 累加入库吨数
        }else {
            product.setStock(stock_in);
        }
        product.setInputTime(new Date());
        product.setUpdatetime(new Date());
        productMapper.insertSelective(product);
    }

    /**
     * @Description 产品出库
     * @author liuli
     * @date 2020/5/21 10:41
     * @param productName 产品名称
     * @param stock_out 出库吨数
     * @return int
     **/
    @Override
    public int out(String productName, int stock_out) {
        Product p = new Product();
        p.setName(productName);
        Product produc = productMapper.selectOne(p);
        if (produc != null) {
            if (stock_out > produc.getStock()){
                return 3;
            }
            Product product = new Product();
            product.setName(productName);
            product.setStock(produc.getStock()-stock_out);
            product.setStockOut(produc.getStockOut()+stock_out); // 累加出库吨数
            product.setInputTime(new Date());
            product.setUpdatetime(new Date());
            productMapper.insertSelective(product);
            return 1;
        }else {
            return 2;
        }
    }

    /**
     * @Description 产品列表查询
     * @author liuli
     * @date 2020/5/22 10:17
     * @param name 产品名
     * @param inputStartTime 入库起始时间
     * @param inputEndTime 入库结束时间
     * @param outStartTime 出库起始时间
     * @param outEndTime 出库结束时间
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Product>
     **/
    @Override
    public PageInfo<Product> findAll(String name, String inputStartTime,String inputEndTime,String outStartTime,String outEndTime,int page, int size) {
        PageHelper.startPage(page,size);
        List<Product> products = productMapper.findAll(name,inputStartTime,inputEndTime,outStartTime,outEndTime);
        return new PageInfo<Product>(products);
    }

    /**
     * @Description 产品查询对象构建
     * @author liuli
     * @date 2020/5/21 11:16
     * @param product
     * @return tk.mybatis.mapper.entity.Example
     **/
    private Example createExample(Product product){
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        if (product != null) {
            if (!StringUtils.isEmpty(product.getId())){
                criteria.andEqualTo("id",product.getId());
            }
            if (!StringUtils.isEmpty(product.getName())){
                criteria.andEqualTo("name",product.getName());
            }
            if (!StringUtils.isEmpty(product.getInputTime())){
                criteria.andEqualTo("inputTime",product.getInputTime());
            }
            if (!StringUtils.isEmpty(product.getStockIn())){
                criteria.andEqualTo("stockIn",product.getStockIn());
            }
            if (!StringUtils.isEmpty(product.getStockOut())){
                criteria.andEqualTo("stockOut",product.getStockOut());
            }
            if (!StringUtils.isEmpty(product.getStock())){
                criteria.andEqualTo("stock",product.getStock());
            }
            if (!StringUtils.isEmpty(product.getUpdatetime())){
                criteria.andEqualTo("updatetime",product.getUpdatetime());
            }
        }
        return example;
    }
}
