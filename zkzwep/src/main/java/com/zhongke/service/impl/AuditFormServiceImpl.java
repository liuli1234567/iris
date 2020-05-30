package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.entity.AuditFormPojo;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.DomainName;
import com.zhongke.entity.SendMessage;
import com.zhongke.mapper.AccessTokenMapper;
import com.zhongke.mapper.AuditFormMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.AccessToken;
import com.zhongke.pojo.AuditForm;
import com.zhongke.service.AuditFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${html.server.ip}")
    private String htmlServerIp;

    @Autowired(required = false)
    private AuditFormMapper auditFormMapper;
    @Autowired(required = false)
    private AccessTokenMapper accessTokenMapper;

    /**
     * @Description 获取项目打包后在服务器的项目路径
     * @author liuli
     * @date 2020/5/29 9:55
     * @param
     * @return java.lang.String
     **/
    public String getProjectRootPath(){
        // 获取项目路径（兼容war服务和jar服务）
        String rootPath = this.getClass().getResource("/").getPath();
        String[] splits = rootPath.split("/");
        int breakI = 0;
        boolean isJarService = false;// 项目是否是打成jar包发布
        for (int i = 0; i < splits.length; i++) {
            if (splits[i].contains(".jar")){
                breakI = i;
                isJarService = true;
                break;
            }
        }
        String finalRootPath = "";
        if (isJarService){
            // 打成jar包后，获取的路径中会以file:XXX/XXX开头，所以去除切割后的第一个路径，i从1开始遍历
            for (int i = 1; i < breakI; i++) {
                finalRootPath += splits[i] + "/";
            }
        } else {
            finalRootPath = rootPath;
        }
        return finalRootPath;
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
        // 从数据库获取access_token
        AccessToken token = new AccessToken();
        token.setId(1);
        AccessToken accessToken = accessTokenMapper.selectByPrimaryKey(token);

        AuditForm auditForm = new AuditForm();
        auditForm.setId(id);
        if (1 == status){
            // todo 向公众号发送客户资料审核通过通知
            AuditForm audit = auditFormMapper.selectByPrimaryKey(auditForm);
            String clientOpenid = audit.getClientOpenid();
            new SendMessage().sendDataTrueMessage(accessToken.getAccessToken(),clientOpenid, htmlServerIp+"/#/contractDownload");
        }
        if (2 == status){
            // todo 向公众号发送客户资料审核不通过通知
            AuditForm audit = auditFormMapper.selectByPrimaryKey(auditForm);
            String clientOpenid = audit.getClientOpenid();
            new SendMessage().sendDataFalseMessage(accessToken.getAccessToken(), clientOpenid);
        }
        auditForm.setStatus(status);
        auditFormMapper.updateByPrimaryKeySelective(auditForm);
    }

    /**
     * @Description 公众号客户提交审核资料
     * @author liuli
     * @date 2020/5/26 14:49
     * @param auditFormPojo 参数封装实体类
     * @return void
     **/
    @Override
    public void add(AuditFormPojo auditFormPojo) {
        AuditForm auditForm = new AuditForm();
        auditForm.setClientOpenid(auditFormPojo.getClientOpenid());
        auditForm.setClientPhone(auditFormPojo.getClientPhone());
        auditForm.setClientName(auditFormPojo.getClientName());
        auditForm.setStatus(0);
        auditForm.setUpdatetime(new Date());
        auditForm.setCreateTime(new Date());
        Base64.Decoder decoder = Base64.getDecoder();
        // 营业执照图片
        for (String business : auditFormPojo.getBusinessList()) {
            if (business.length() > 200){
                String s = business.split(",")[1];
                byte[] bytes = decoder.decode(s);
                FileOutputStream fos = null;
                String path = null;
                try {
                    path = getProjectRootPath() + "static/images/"+ DateUtil.getTime()+".jpg";
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
                auditForm.setBusinessLicense((auditForm.getBusinessLicense()==null?"":auditForm.getBusinessLicense())+ DomainName.imagesDomainName +path.substring((getProjectRootPath() + "static/images/").length())+",");
            }else {
                auditForm.setBusinessLicense((auditForm.getBusinessLicense()==null?"":auditForm.getBusinessLicense())+business+",");
            }
        }
        // 生产经营许可证图片
        for (String prod : auditFormPojo.getProdOperList()) {
            if (prod.length() > 200){
                String s = prod.split(",")[1];
                byte[] bytes = decoder.decode(s);
                FileOutputStream fos = null;
                String path = null;
                try {
                    path = getProjectRootPath() + "static/images/"+ DateUtil.getTime()+".jpg";
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
                auditForm.setProdOperLicence((auditForm.getProdOperLicence()==null?"":auditForm.getProdOperLicence())+DomainName.imagesDomainName+path.substring((getProjectRootPath() + "static/images/").length())+",");
            }else {
                auditForm.setProdOperLicence((auditForm.getProdOperLicence()==null?"":auditForm.getProdOperLicence())+prod+",");
            }
        }
        // 医疗器械许可证图片
        for (String medical : auditFormPojo.getMedicalDevList()) {
            if (medical.length() > 200){
                String s = medical.split(",")[1];
                byte[] bytes = decoder.decode(s);
                FileOutputStream fos = null;
                String path = null;
                try {
                    path = getProjectRootPath() + "static/images/"+ DateUtil.getTime()+".jpg";
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
                auditForm.setMedicalDevLicense((auditForm.getMedicalDevLicense()==null?"":auditForm.getMedicalDevLicense())+DomainName.imagesDomainName+path.substring((getProjectRootPath() + "static/images/").length())+",");
            }else {
                auditForm.setMedicalDevLicense((auditForm.getMedicalDevLicense()==null?"":auditForm.getMedicalDevLicense())+medical+",");
            }
        }
        // 申购函文件
        String s = auditFormPojo.getLetter().split(",")[1];
        byte[] bytes = decoder.decode(s);
        FileOutputStream fos = null;
        String path = null;
        try {
            path = getProjectRootPath() + "static/word/"+ DateUtil.getTime()+".docx";
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
        auditForm.setLetterUrl(DomainName.wordDomainName+path.substring((getProjectRootPath() + "static/word/").length()));
        auditFormMapper.insertSelective(auditForm);
    }

    /**
     * @Description 公众号回显用户审核资料
     * @author liuli
     * @date 2020/5/26 15:54
     * @param openid
     * @return com.zhongke.pojo.AuditForm
     **/
    @Override
    public AuditForm findByOpenid(String openid) {
        return auditFormMapper.findByOpenid(openid);
    }
}
