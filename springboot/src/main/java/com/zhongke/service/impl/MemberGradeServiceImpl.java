package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.MemberGradeMapper;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.MemberGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName MemberGradeServiceImpl
 * @Description 会员等级
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Service
public class MemberGradeServiceImpl implements MemberGradeService {

    @Autowired(required = false)
    private MemberGradeMapper memberGradeMapper;

    @Override
    public PageInfo<MemberGrade> memGrades(MemberGrade memberGrade, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(memberGrade);
        List<MemberGrade> memberGrades = memberGradeMapper.selectByExample(example);
        return new PageInfo<>(memberGrades);
    }

    @Override
    public void update(MemberGrade memberGrade) {
        memberGradeMapper.updateByPrimaryKey(memberGrade);
    }

    @Override
    public void add(MemberGrade memberGrade) {
        memberGradeMapper.insert(memberGrade);
    }

    @Override
    public void delete(int id) {
        memberGradeMapper.deleteByPrimaryKey(id);
    }

    public Example createExample(MemberGrade memberGrade){
        Example example = new Example(MemberGrade.class);
        Example.Criteria criteria = example.createCriteria();
        if (memberGrade!=null){
            // 会员等级id
            if (!StringUtils.isEmpty(memberGrade.getId())){
                criteria.andEqualTo("id",memberGrade.getId());
            }
            // 等级名称
            if (!StringUtils.isEmpty(memberGrade.getName())){
                criteria.andEqualTo("name",memberGrade.getName());
            }
            // 等级图片
            if (!StringUtils.isEmpty(memberGrade.getImage())){
                criteria.andEqualTo("image",memberGrade.getImage());
            }
            // 特权说明
            if (!StringUtils.isEmpty(memberGrade.getPrivilege())){
                criteria.andEqualTo("privilege",memberGrade.getPrivilege());
            }
            // 充值金额
            if (!StringUtils.isEmpty(memberGrade.getRechargeMoney())){
                criteria.andEqualTo("rechargeMoney",memberGrade.getRechargeMoney());
            }
            // 消费折扣
            if (!StringUtils.isEmpty(memberGrade.getConsumDiscount())){
                criteria.andEqualTo("consumDiscount",memberGrade.getConsumDiscount());
            }
            // 赠送金额
            if (!StringUtils.isEmpty(memberGrade.getGiveMoney())){
                criteria.andEqualTo("giveMoney",memberGrade.getGiveMoney());
            }
            // 赠送积分
            if (!StringUtils.isEmpty(memberGrade.getGiveIntegral())){
                criteria.andEqualTo("giveIntegral",memberGrade.getGiveIntegral());
            }
            // 商户id
            if (!StringUtils.isEmpty(memberGrade.getMerchantId())){
                criteria.andEqualTo("merchantId",memberGrade.getMerchantId());
            }
            // 更新时间
            if (!StringUtils.isEmpty(memberGrade.getCreateTime())){
                criteria.andEqualTo("createTime",memberGrade.getCreateTime());
            }
        }
        return example;
    }
}