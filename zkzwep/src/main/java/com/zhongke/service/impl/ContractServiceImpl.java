package com.zhongke.service.impl;

import com.zhongke.entity.DateUtil;
import com.zhongke.mapper.ContractMapper;
import com.zhongke.pojo.Contract;
import com.zhongke.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

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

    /**
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @param openid 用户openid
     * @param userContract 只有客户章合同
     * @return void
     **/
    @Override
    public void add(String openid, String userContract) {
        Contract contract = new Contract();
        contract.setClientOpenid(openid);
        contract.setStatus(0);
        contract.setUpdatetime(new Date());
        contract.setCreateTime(new Date());
        Base64.Decoder decoder = Base64.getDecoder();
        String s = userContract.split(",")[1];
        byte[] bytes = decoder.decode(s);
        FileOutputStream fos = null;
        String path = null;
        try {
            path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\word\\"+ DateUtil.getTime()+".docx";
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
        contract.setContractClient(path);
        contractMapper.insertSelective(contract);
    }
}
