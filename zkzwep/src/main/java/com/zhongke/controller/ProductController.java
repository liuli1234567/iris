package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.Product;
import com.zhongke.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ProductController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired(required = false)
    private ProductService productService;

    /**
     * @Description 添加产品
     * @author liuli
     * @date 2020/5/21 10:24
     * @param productName 产品名称
     * @param stock_in  产品吨数
     * @param userId 操作人id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/product_add")
    public Result product_add(@RequestParam String productName,@RequestParam int stock_in,@RequestParam int userId){
        productService.add(productName,stock_in,userId);
        return new Result(StatusCode.SUCCESS,"入库成功");
    }

    /**
     * @Description 产品出库
     * @author liuli
     * @date 2020/5/21 10:40
     * @param id 产品id
     * @param stock_out 出库吨数
     * @param userId 操作人id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/product_update")
    public Result product_update(@RequestParam int id,@RequestParam int stock_out,@RequestParam int userId){
        int flag = productService.update(id, stock_out,userId);
        if (1 == flag){
            return new Result(StatusCode.SUCCESS,"出库成功");
        }
        if (2 == flag){
            return new Result(StatusCode.SUCCESS,"出库失败，产品未找到");
        }else {
            return new Result(StatusCode.SUCCESS,"出库失败，库存不足！");
        }
    }

    /**
     * @Description 产品入库
     * @author liuli
     * @date 2020/5/21 10:24
     * @param id 产品id
     * @param stock_in  入库吨数
     * @param userId 操作人id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/product_in")
    public Result product_in(@RequestParam int id ,@RequestParam int stock_in,@RequestParam int userId){
        productService.in(id,stock_in,userId);
        return new Result(StatusCode.SUCCESS,"入库成功");
    }

    /**
     * @Description 产品列表查询
     * @author liuli
     * @date 2020/5/22 10:11
     * @param name 产品名称
     * @param inputStartTime 入库起始时间
     * @param inputEndTime 入库结束时间
     * @param outStartTime 出库起始时间
     * @param outEndTime 出库结束时间
     * @param page
     * @param size
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(required = false) String name,@RequestParam(required = false) String inputStartTime,
                          @RequestParam(required = false) String inputEndTime,@RequestParam(required = false) String outStartTime,
                          @RequestParam(required = false) String outEndTime,@RequestParam int page,@RequestParam int size){
        PageInfo<Product> products = productService.findAll(name,inputStartTime,inputEndTime,outStartTime,outEndTime,page,size);
        return new Result(StatusCode.SUCCESS,"查询成功",products);
    }
}
