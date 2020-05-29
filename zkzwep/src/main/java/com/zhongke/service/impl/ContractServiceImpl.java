package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.DomainName;
import com.zhongke.entity.SendMessage;
import com.zhongke.mapper.AccessTokenMapper;
import com.zhongke.mapper.AuditFormMapper;
import com.zhongke.mapper.ContractMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.AccessToken;
import com.zhongke.pojo.AuditForm;
import com.zhongke.pojo.Contract;
import com.zhongke.pojo.Order;
import com.zhongke.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ContractServiceImpl
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:09
 * @Version 1.0
 **/
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired(required = false)
    private ContractMapper contractMapper;
    @Autowired(required = false)
    private AuditFormMapper auditFormMapper;
    @Autowired(required = false)
    private AccessTokenMapper accessTokenMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;

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
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @param openid 用户openid
     * @param userContract 只有客户章合同
     * @return void
     **/
    @Override
    public void public_add(String openid, String userContract) {
        AuditForm auditForm = auditFormMapper.findByOpenid(openid);
        if (auditForm != null) {
            Contract contract = new Contract();
            contract.setContractNo(DateUtil.getTime());
            contract.setClientOpenid(openid);
            contract.setClientName(auditForm.getClientName());
            contract.setClientPhone(auditForm.getClientPhone());
            contract.setStatus(0);
            contract.setUpdatetime(new Date());
            contract.setCreateTime(new Date());
            Base64.Decoder decoder = Base64.getDecoder();
            String s = userContract.split(",")[1];
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
            contract.setContractClient(DomainName.wordDomainName +path.substring((getProjectRootPath() + "static/images/").length()));
            contractMapper.insertSelective(contract);
        }
    }

    /**
     * @Description 公众号客户下载合同
     * @author liuli
     * @date 2020/5/25 9:35
     * @param flag 合同标记 0 现货合同 1 预售合同
     * @return void
     **/
    @Override
    public void public_download(int flag, HttpServletResponse response) {
        // 下载本地文件
        String fileName = "熔喷布项目计划文档.docx"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = null;// 文件的存放路径
        try {
            inStream = new FileInputStream(getProjectRootPath() + "static/word/"+"熔喷布项目计划文档.docx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * @return com.github.pagehelper.PageInfo<com.zhongke.pojo.Contract>
     **/
    @Override
    public PageInfo<Contract> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size) {
        PageHelper.startPage(page,size);
        if (!StringUtils.isEmpty(startTime)){
            startTime = startTime+" 00:00:00";
        }
        if (!StringUtils.isEmpty(endTime)){
            endTime = endTime+"23:59:59";
        }
        List<Contract> auditForms = contractMapper.findAll(nameOrPhone,status,startTime,endTime);
        return new PageInfo<>(auditForms);
    }

    /**
     * @Description 后台客服上传合同
     * @author liuli
     * @date 2020/5/25 10:42
     * @param id
     * @param contract_all 双方章合同
     * @return void
     **/
    @Override
    public void customer_add(Integer id, String contract_all) {
        Contract cont = new Contract();
        cont.setId(id);
        cont.setUpdatetime(new Date());
        cont.setContractAll(contract_all);
        contractMapper.updateByPrimaryKeySelective(cont);
    }

    /**
     * @Description 后台客服审核合同
     * @author liuli
     * @date 2020/5/25 11:05
     * @param id
     * @param status 状态码
     * @return int
     **/
    @Override
    public int update(int id, int status) {
        // 从数据库获取access_token
        AccessToken token = new AccessToken();
        token.setId(1);
        AccessToken accessToken = accessTokenMapper.selectByPrimaryKey(token);

        Contract con = new Contract();
        con.setId(id);
        Contract contract = contractMapper.selectByPrimaryKey(con);
        if (contract != null) {
            if (2 == status){
                contract.setStatus(status);
                contract.setUpdatetime(new Date());
                contractMapper.updateByPrimaryKeySelective(contract);
                // todo 通知公众号此合同驳回
                Contract cont = contractMapper.selectByPrimaryKey(con);
                String clientOpenid = cont.getClientOpenid();
                SendMessage.sendContractFalseMessage(accessToken.getAccessToken(), clientOpenid, "");
                return 1;
            }else if (1 == status){
                if (contract.getContractAll() != null){
                    contract.setStatus(status);
                    contract.setUpdatetime(new Date());
                    contractMapper.updateByPrimaryKeySelective(contract);
                    // todo 通知公众号此合同通过
                    Contract cont = contractMapper.selectByPrimaryKey(con);
                    String clientOpenid = cont.getClientOpenid();
                    SendMessage.sendContractTrueMessage(accessToken.getAccessToken(),  clientOpenid, "");
                    // 合同审核通过后生成订单
                    Order order = new Order();
                    order.setOpenid(clientOpenid);
                    order.setName(contract.getClientName());
                    order.setPhone(contract.getClientPhone());
                    order.setOrderNo(DateUtil.getTime());
                    order.setStatus(0); // 订单状态为 0 未支付
                    order.setContractNo(contract.getContractNo());
                    order.setUpdatetime(new Date());
                    order.setCreateTime(new Date());
                    orderMapper.insertSelective(order);
                    return 1;
                }else {
                    return -1; // 未提交双方章合同，不能审核通过
                }
            }else {
                return 1;
            }
        }else {
            return -2;
        }
    }
}
