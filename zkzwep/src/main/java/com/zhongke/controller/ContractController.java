package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.AuditForm;
import com.zhongke.pojo.Contract;
import com.zhongke.service.ContractService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContractController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired(required = false)
    private ContractService contractService;

    /**
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @return com.zhongke.entity.Result
     **/
    @PostMapping("/public_add")
    public Result public_add(@RequestBody Map<String,String> map){
        contractService.public_add(map.get("openid"),map.get("userContract"));
        return new Result(StatusCode.SUCCESS,"上传成功");
    }

    /**
     * @Description 公众号客户下载合同
     * @author liuli
     * @date 2020/5/25 9:35
     * @param flag 合同标记 0 现货合同 1 预售合同
     * @return com.zhongke.entity.Result
     **/
    @ApiOperation(value = "公众号客户下载合同",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flag",value = "合同标记 0 现货合同 1 预售合同",required = true,dataType = "int",paramType = "path"),
    })
    @GetMapping("/public_download")
    public Result public_download(@RequestParam int flag,HttpServletResponse response){
        contractService.public_download(flag,response);
        return new Result(StatusCode.SUCCESS,"下载成功");
    }

    /**
     * @Description 客户合同列表查询
     * @author liuli
     * @date 2020/5/21 13:42
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param startTime 合同创建起始时间
     * @param endTime 合同创建结束时间
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(required = false) String nameOrPhone, @RequestParam(required = false) Integer status,
                          @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
                          @RequestParam int page,@RequestParam int size){
        PageInfo<Contract> auditFormPageInfo = contractService.findAll(nameOrPhone,status,startTime,endTime,page,size);
        return new Result(StatusCode.SUCCESS,"查询成功",auditFormPageInfo);
    }

    /**
     * @Description 后台客服上传合同
     * @author liuli
     * @date 2020/5/25 10:42
     * @param id
     * @param contract_all 双方章合同
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/customer_add")
    public Result public_add(@RequestParam Integer id,@RequestParam String contract_all){
        contractService.customer_add(id,contract_all);
        return new Result(StatusCode.SUCCESS,"上传成功");
    }

    /**
     * @Description 后台客服审核合同
     * @author liuli
     * @date 2020/5/25 11:04
     * @param status 状态码
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/update")
    public Result update(@RequestParam int status,@RequestParam int id){
        int flag = contractService.update(id, status);
        if (-1 == flag){
            return new Result(StatusCode.FALL,"未提交双方章合同，不能审核通过");
        }
        return new Result(StatusCode.SUCCESS,"审核成功");
    }
}
