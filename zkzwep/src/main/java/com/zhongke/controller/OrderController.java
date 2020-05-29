package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.Order;
import com.zhongke.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @Description 通过公众号用户openid查询订单列表
     * @author liuli
     * @date 2020/5/26 18:30
     * @param openid
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findByOpenid")
    public Result findByOpenid(@RequestParam String openid){
        List<Order> orders = orderService.findByOpenid(openid);
        return new Result(StatusCode.SUCCESS,"查询成功",orders);
    }

    /**
     * @Description 通过订单id查询订单信息
     * @author liuli
     * @date 2020/5/26 18:40
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findById")
    public Result findById(@RequestParam int id){
        Order order = orderService.findById(id);
        return new Result(StatusCode.SUCCESS,"查询成功",order);
    }

    /**
     * @Description 通过订单id查询订单是否已出货
     * @author liuli
     * @date 2020/5/26 19:08
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findOutById")
    public Result findOutById(@RequestParam int id){
        int flag = orderService.findOutById(id);
        if (1 == flag){
            return new Result(StatusCode.FALL,"此订单已取货，请勿重复取货");
        }
        if (0 == flag){
            return new Result(StatusCode.SUCCESS,"取货成功");
        }else {
            return new Result(StatusCode.FALL,"系统错误，请联系管理员");
        }
    }

    /**
     * @Description 客服确认订单前提交合同资料
     * @author liuli
     * @date 2020/5/26 18:46
     * @param orderId 订单id
     * @param product_name 产品名称
     * @param product_number 产品吨数
     * @param person_name 联系人姓名
     * @param person_phone 联系人电话
     * @param address 自提地址
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/upload_data")
    public Result upload_data(@RequestParam int orderId,@RequestParam String product_name,@RequestParam int product_number,
                              @RequestParam String person_name,@RequestParam String person_phone,
                              @RequestParam String address){
        orderService.upload_data(orderId,product_name,product_number,person_name,person_phone,address);
        return new Result(StatusCode.SUCCESS,"添加成功");
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
