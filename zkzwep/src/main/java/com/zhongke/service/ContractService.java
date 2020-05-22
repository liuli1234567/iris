package com.zhongke.service;

public interface ContractService {
    /**
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @param openid 用户openid
     * @param userContract 只有客户章合同
     * @return void
     **/
    void add(String openid, String userContract);

}
