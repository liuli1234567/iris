package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.FacPerson;
import com.zhongke.service.FacPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FacPersonController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/facPerson")
public class FacPersonController {

    @Autowired(required = false)
    private FacPersonService facPersonService;

    /**
     * @Description 工厂人员信息录入
     * @author liuli
     * @date 2020/5/21 11:26
     * @param openid 工厂人openid
     * @param name 姓名
     * @param phone 手机号
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/fac_person_input")
    public Result facPersonInput(@RequestParam String openid,@RequestParam String name,@RequestParam String phone){
        facPersonService.add(openid,name,phone);
        return new Result(StatusCode.SUCCESS,"录入成功");
    }

    /**
     * @Description 工厂人员列表查询
     * @author liuli
     * @date 2020/5/22 10:33
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param page
     * @param size
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/fac_person_query")
    public Result facPersonQuery(@RequestParam(required = false) String nameOrPhone, @RequestParam(required = false) int status,
                                 @RequestParam int page,@RequestParam int size){
        PageInfo<FacPerson> facPersonPageInfo = facPersonService.query(nameOrPhone, status,page,size);
        return new Result(StatusCode.SUCCESS,"查询成功",facPersonPageInfo);
    }

    /**
     * @Description 修改工厂人员审核状态
     * @author liuli
     * @date 2020/5/21 11:44
     * @param status 状态
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/fac_person_updateById")
    public Result facPersonUpdateById(@RequestParam int status,@RequestParam int id){
        facPersonService.updateById(status,id);
        return new Result(StatusCode.SUCCESS,"修改成功");
    }
}
