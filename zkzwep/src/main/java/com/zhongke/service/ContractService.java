package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Contract;

import javax.servlet.http.HttpServletResponse;

public interface ContractService {
    /**
     * @Description 公众号客服提交合同
     * @author liuli
     * @date 2020/5/22 18:05
     * @param openid 用户openid
     * @param userContract 只有客户章合同
     * @return void
     **/
    void public_add(String openid, String userContract);

    /**
     * @Description 公众号客户下载合同
     * @author liuli
     * @date 2020/5/25 9:35
     * @param flag 合同标记 0 现货合同 1 预售合同
     * @return void
     **/
    void public_download(int flag, HttpServletResponse response);

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
    PageInfo<Contract> findAll(String nameOrPhone, Integer status, String startTime, String endTime, int page, int size);

    /**
     * @Description 后台客服上传合同
     * @author liuli
     * @date 2020/5/25 10:42
     * @param id
     * @param contract_all 双方章合同
     * @return void
     **/
    void customer_add(Integer id, String contract_all);

    /**
     * @Description 后台客服审核合同
     * @author liuli
     * @date 2020/5/25 11:05
     * @param id
     * @param status 状态码
     * @return int
     **/
    int update(int id, int status);
}
