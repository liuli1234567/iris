package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.AuditFormPojo;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import com.zhongke.pojo.AuditForm;
import com.zhongke.service.AuditFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param auditFormPojo 参数封装实体类
     * @return com.zhongke.entity.Result
     **/
    @PostMapping("/add")
    public Result add(@RequestBody AuditFormPojo auditFormPojo){
        auditFormService.add(auditFormPojo);
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
     * @Description 公众号回显用户审核资料
     * @author liuli
     * @date 2020/5/26 15:53
     * @param openid
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findByOpenid")
    public Result findByOpenid(@RequestParam String openid){
        AuditForm auditForm = auditFormService.findByOpenid(openid);
        if (auditForm != null) {
            List<Map<String, String>> businessList = new ArrayList<>();
            String[] businessArray = auditForm.getBusinessLicense().split(",");
            for (String s : businessArray) {
                Map<String, String> businessMap = new HashMap<>();
                businessMap.put("url",s);
                businessList.add(businessMap);
            }
            auditForm.setBusinessList(businessList);
            List<Map<String, String>> prodOperList = new ArrayList<>();
            String[] prodOperArray = auditForm.getProdOperLicence().split(",");
            for (String s : prodOperArray) {
                Map<String, String> prodOperMap = new HashMap<>();
                prodOperMap.put("url",s);
                prodOperList.add(prodOperMap);
            }
            auditForm.setProdOperList(prodOperList);
            List<Map<String, String>> medicalDevList = new ArrayList<>();
            String[] medicalArray = auditForm.getMedicalDevLicense().split(",");
            for (String s : medicalArray) {
                Map<String, String> medicalDevMap = new HashMap<>();
                medicalDevMap.put("url",s);
                medicalDevList.add(medicalDevMap);
            }
            auditForm.setMedicalDevList(medicalDevList);
            return new Result(StatusCode.SUCCESS,"查询成功",auditForm);
        }else {
            return new Result(StatusCode.SUCCESS,"查询成功",null);
        }
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
