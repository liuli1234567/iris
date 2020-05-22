package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.entity.DateUtil;
import com.zhongke.mapper.AuditFormMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.AuditForm;
import com.zhongke.service.AuditFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AuditFormServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:08
 * @Version 1.0
 **/
@Service
public class AuditFormServiceImpl implements AuditFormService {

    @Autowired(required = false)
    private AuditFormMapper auditFormMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;

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
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.AuditForm>
     **/
    @Override
    public PageInfo<AuditForm> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size) {
        PageHelper.startPage(page,size);
        if (!StringUtils.isEmpty(startTime)){
            startTime = startTime+" 00:00:00";
        }
        if (!StringUtils.isEmpty(endTime)){
            endTime = endTime+"23:59:59";
        }
        List<AuditForm> auditForms = auditFormMapper.findAll(nameOrPhone,status,startTime,endTime);
        return new PageInfo<>(auditForms);
    }

    /**
     * @Description 审核客户资料
     * @author liuli
     * @date 2020/5/21 16:01
     * @param id
     * @param status  状态
     * @return void
     **/
    @Override
    public void update(int id, int status) {
        AuditForm auditForm = new AuditForm();
        auditForm.setId(id);
        if (1 == status){
            // todo 向公众号发送客户资料审核通过通知
        }
        if (2 == status){
            // todo 向公众号发送客户资料审核不通过通知
        }
        auditForm.setStatus(status);
        auditFormMapper.updateByPrimaryKeySelective(auditForm);
    }

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
     * @return void
     **/
    @Override
    public void add(String openid, String phone, String name, String[] businessLicense, String[] prodOperLicense, String[] medicalDevLicense, String letter) {
        AuditForm auditForm = new AuditForm();
        auditForm.setClientOpenid(openid);
        auditForm.setClientPhone(phone);
        auditForm.setClientName(name);
        auditForm.setStatus(0);
        auditForm.setUpdatetime(new Date());
        auditForm.setCreateTime(new Date());
        Base64.Decoder decoder = Base64.getDecoder();
        // 营业执照图片
        for (String business : businessLicense) {
            String s = business.split(",")[1];
            byte[] bytes = decoder.decode(s);
            FileOutputStream fos = null;
            String path = null;
            try {
                path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"+ DateUtil.getTime()+".jpg";
                fos = new FileOutputStream(path);
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            auditForm.setBusinessLicense(path);
        }
        // 生产经营许可证图片
        for (String prod : prodOperLicense) {
            String s = prod.split(",")[1];
            byte[] bytes = decoder.decode(s);
            FileOutputStream fos = null;
            String path = null;
            try {
                path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"+ DateUtil.getTime()+".jpg";
                fos = new FileOutputStream(path);
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            auditForm.setProdOperLicence(path);
        }
        // 医疗器械许可证图片
        for (String medical : medicalDevLicense) {
            String s = medical.split(",")[1];
            byte[] bytes = decoder.decode(s);
            FileOutputStream fos = null;
            String path = null;
            try {
                path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"+ DateUtil.getTime()+".jpg";
                fos = new FileOutputStream(path);
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            auditForm.setProdOperLicence(path);
        }
    }
}
