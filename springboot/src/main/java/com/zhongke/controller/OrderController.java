package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Order;
import com.zhongke.service.OrderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OrderController
 * @Description 订单
 * @Author liuli
 * @Date 2020/4/1 9:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
@Api(value = "订单相关")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * @Description 查询全部订单
     * @author liuli
     * @date 2020/4/1 10:13
     * @param
     * @return com.zhongke.entity.Result<com.zhongke.pojo.Order>
     **/
    @GetMapping("/findAll")
    public Result<Order> findAll(){
        try {
            List<Order> orders = orderService.findAll();
            return new Result<>(0,"查询订单列表成功",orders);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("OrderController.findAll(): "+e.getMessage());
            return new Result<>(-1,"查询订单列表失败："+e.getMessage());
        }
    }

    /**
     * @Description 通过条件查询订单列表并分页
     * @author liuli
     * @date 2020/4/1 10:24
     * @param order
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Order>>
     **/
    @PostMapping("/findOrdersByExample/{page}/{size}")
    public Result<List<Order>> findOrdersByExample(@RequestBody(required = false) Order order,
                                                   @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Order> orders = orderService.findOrdersByExample(order,page,size);
            return new Result<>(0,"通过条件查询订单列表成功",orders);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("OrderController.findOrdersByExample(): "+e.getMessage());
            return new Result<>(-1,"通过条件查询订单列表失败:"+e.getMessage());
        }
    }
}
