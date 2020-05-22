package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.AuditForm;
import com.zhongke.service.AuditFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * @ClassName AuditFormController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/auditForm")
public class AuditFormController {

    @Autowired(required = false)
    private AuditFormService auditFormService;

    /**
     * @Description 公众号客户提交审核资料
     * @author liuli
     * @date 2020/5/22 16:00
     * @param openid 用户openid
     * @param phone 手机号
     * @param name 姓名
     * @param businessLicense 营业执照图片数组
     * @param prodOperLicense 生产经营许可证图片数组
     * @param medicalDevLicense 医疗器械许可证图片数组
     * @param letter 申购函文件
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/add")
    public Result findAll(@RequestParam String openid,@RequestParam String phone,@RequestParam String name,
                          @RequestParam String[] businessLicense,@RequestParam String[] prodOperLicense,
                          @RequestParam String[] medicalDevLicense,@RequestParam String letter){
        auditFormService.add(openid,phone,name,businessLicense,prodOperLicense,medicalDevLicense,letter);
        return new Result(StatusCode.SUCCESS,"添加成功");
    }

    /**
     * @Description 客户资料审核表列表查询
     * @author liuli
     * @date 2020/5/21 13:42
     * @param nameOrPhone 姓名或手机号
     * @param status 审核状态
     * @param startTime 资料创建起始时间
     * @param endTime 资料创建结束时间
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(required = false) String nameOrPhone, @RequestParam(required = false) Integer status,
                          @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
                          @RequestParam int page,@RequestParam int size){
        PageInfo<AuditForm> auditFormPageInfo = auditFormService.findAll(nameOrPhone,status,startTime,endTime,page,size);
        List<AuditForm> list = auditFormPageInfo.getList();
        if (list != null && list.size() > 0) {
            for (AuditForm auditForm : list) {
                String[] businessArray = auditForm.getBusinessLicense().split(",");
                auditForm.setBusinessArray(businessArray);
                String[] prodOperArray = auditForm.getProdOperLicence().split(",");
                auditForm.setProdOperArray(prodOperArray);
                String[] medicalArray = auditForm.getMedicalDevLicense().split(",");
                auditForm.setMedicalDevArray(medicalArray);
            }
        }
        return new Result(StatusCode.SUCCESS,"查询成功",auditFormPageInfo);
    }

    /**
     * @Description 审核客户资料
     * @author liuli
     * @date 2020/5/21 16:01
     * @param status 状态
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/update")
    public Result update(@RequestParam int status,@RequestParam int id){
        auditFormService.update(id,status);
        return new Result(StatusCode.SUCCESS,"审核成功");
    }
}
