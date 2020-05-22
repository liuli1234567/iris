package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.Order;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * @Description 查询取货码
     * @author liuli
     * @date 2020/5/21 10:03
     * @param orderNo
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/pickup_code_query")
    public Result pickupCodeQuery(@RequestParam String orderNo){
        Order order = orderService.findOrderCodeByOrderNo(orderNo);
        if (order != null) {
            return new Result(StatusCode.SUCCESS,"取货码发送成功",order);
        }else {
            return new Result(StatusCode.FALL,"该订单不存在或未经客服确认！");
        }
    }

    /**
     * @Description 订单列表查询
     * @author liuli
     * @date 2020/5/21 16:43
     * @param nameOrPhone 客户姓名或手机号
     * @param status 订单状态
     * @param startTime 订单创建时间起始时间
     * @param endTime 订单创建时间结束时间
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(required = false) String nameOrPhone, @RequestParam(required = false) Integer status,
                          @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
                          @RequestParam int page,@RequestParam int size){
        PageInfo<Order> orders = orderService.findAll(nameOrPhone,status,startTime,endTime,page,size);
        return new Result(StatusCode.SUCCESS,"查询成功",orders);
    }

    /**
     * @Description 财务修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/finance_update")
    public Result financeUpdate(@RequestParam int status, @RequestParam int id){
        orderService.financeUpdate(status,id);
        return new Result(StatusCode.SUCCESS,"修改成功");
    }

    /**
     * @Description 客服修改订单状态
     * @author liuli
     * @date 2020/5/21 17:12
     * @param status 状态
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/customer_update")
    public Result customerUpdate(@RequestParam int status, @RequestParam int id){
        orderService.customerUpdate(status,id);
        return new Result(StatusCode.SUCCESS,"修改成功");
    }
}
