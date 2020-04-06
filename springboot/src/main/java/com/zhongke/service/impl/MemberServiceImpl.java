package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.MemberGradeMapper;
import com.zhongke.mapper.MemberMapper;
import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Member;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName MemberServiceImpl
 * @Description 会员
 * @Author 一只逆袭的程序猿
 * @CreateDate 2020/4/5
 * @Version 2.1
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired(required = false)
    private MemberMapper memberMapper;
    @Autowired(required = false)
    private MemberGradeMapper memberGradeMapper;
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Member> memGrades(Member member, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if (member != null) {
            if (!StringUtils.isEmpty(member.getMemNum())){
                criteria.andEqualTo("memNum",member.getMemNum());
            }
            if (!StringUtils.isEmpty(member.getTel())){
                criteria.andEqualTo("tel",member.getTel());
            }if (!StringUtils.isEmpty(member.getStartTime())){
                if (!StringUtils.isEmpty(member.getEndTime())){
                    criteria.andBetween("createTime",member.getStartTime()+" 00:00:00",member.getEndTime()+" 23:59:59");
                }else {
                    criteria.andGreaterThanOrEqualTo("createTime",member.getStartTime()+" 00:00:00");
                }
            }else {
                if (!StringUtils.isEmpty(member.getEndTime())){
                    criteria.andLessThanOrEqualTo("createTime",member.getEndTime()+" 23:59:59");
                }
            }
        }
        List<Member> members = memberMapper.selectByExample(example);
        if (members != null && members.size()>0) {
            for (Member member1 : members) {
                if (member1.getGradeId() != null){
                    MemberGrade memberGrade = memberGradeMapper.selectByPrimaryKey(member1.getGradeId());
                    if (!StringUtils.isEmpty(memberGrade.getName())){
                        member1.setGradeName(memberGrade.getName());
                    }
                }
                BigDecimal bigDecimal = orderMapper.findConsumByMemberId(member1.getId());
                member1.setLastConsum(bigDecimal);
            }
        }
        return new PageInfo<>(members);
    }

    @Override
    public Member findById(int id) {
        Member member = memberMapper.selectByPrimaryKey(id);
        if (member != null) {
            if (member.getGradeId() != null){
                MemberGrade memberGrade = memberGradeMapper.selectByPrimaryKey(member.getGradeId());
                if (!StringUtils.isEmpty(memberGrade.getName())) {
                    member.setGradeName(memberGrade.getName());
                }
            }
        }
        return member;
    }

    @Override
    public void update(Member member) {
        memberMapper.updateByPrimaryKey(member);
    }

    @Override
    public Map analysis(String startTime, String endTime) {
        Integer addSum = memberMapper.analysis(startTime,endTime);
        Integer memCount = memberMapper.selectCount(new Member());
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("addSum",addSum); // 新增会员数
        hashMap.put("memCount",memCount); // 会员总数
        return hashMap;
    }

    @Override
    public Map analysisByTime(String startTime, String endTime) {
        Map map = getMap(startTime, endTime);
        return map;
    }

    private Map getMap(String startTime, String endTime) {
        int addSum = 0;
        Integer sum = memberMapper.findCountByTime(startTime +" 00:00:00");

        Integer addSum2 = memberMapper.analysis(startTime + " 00:00:00", endTime + " 01:00:00");
        Integer sum2 = memberMapper.findCountByTime(startTime +" 01:00:00");

        Integer addSum3 = memberMapper.analysis(startTime + " 01:00:00", endTime + " 02:00:00");
        Integer sum3 = memberMapper.findCountByTime(startTime +" 02:00:00");

        Integer addSum4 = memberMapper.analysis(startTime + " 02:00:00", endTime + " 03:00:00");
        Integer sum4 = memberMapper.findCountByTime(startTime +" 03:00:00");

        Integer addSum5 = memberMapper.analysis(startTime + " 03:00:00", endTime + " 04:00:00");
        Integer sum5 = memberMapper.findCountByTime(startTime +" 04:00:00");

        Integer addSum6 = memberMapper.analysis(startTime + " 04:00:00", endTime + " 05:00:00");
        Integer sum6 = memberMapper.findCountByTime(startTime +" 05:00:00");

        Integer addSum7 = memberMapper.analysis(startTime + " 05:00:00", endTime + " 06:00:00");
        Integer sum7 = memberMapper.findCountByTime(startTime +" 06:00:00");

        Integer addSum8 = memberMapper.analysis(startTime + " 06:00:00", endTime + " 07:00:00");
        Integer sum8 = memberMapper.findCountByTime(startTime +" 07:00:00");

        Integer addSum9 = memberMapper.analysis(startTime + " 07:00:00", endTime + " 08:00:00");
        Integer sum9 = memberMapper.findCountByTime(startTime +" 08:00:00");

        Integer addSuma = memberMapper.analysis(startTime + " 08:00:00", endTime + " 09:00:00");
        Integer suma = memberMapper.findCountByTime(startTime +" 09:00:00");

        Integer addSumb = memberMapper.analysis(startTime + " 09:00:00", endTime + " 10:00:00");
        Integer sumb = memberMapper.findCountByTime(startTime +" 10:00:00");

        Integer addSumc = memberMapper.analysis(startTime + " 10:00:00", endTime + " 11:00:00");
        Integer sumc = memberMapper.findCountByTime(startTime +" 11:00:00");

        Integer addSumd = memberMapper.analysis(startTime + " 11:00:00", endTime + " 12:00:00");
        Integer sumd = memberMapper.findCountByTime(startTime +" 12:00:00");

        Integer addSume = memberMapper.analysis(startTime + " 12:00:00", endTime + " 13:00:00");
        Integer sume = memberMapper.findCountByTime(startTime +" 13:00:00");

        Integer addSumf = memberMapper.analysis(startTime + " 13:00:00", endTime + " 14:00:00");
        Integer sumf = memberMapper.findCountByTime(startTime +" 14:00:00");

        Integer addSumg = memberMapper.analysis(startTime + " 14:00:00", endTime + " 15:00:00");
        Integer sumg = memberMapper.findCountByTime(startTime +" 15:00:00");

        Integer addSumh = memberMapper.analysis(startTime + " 15:00:00", endTime + " 16:00:00");
        Integer sumh = memberMapper.findCountByTime(startTime +" 16:00:00");

        Integer addSumi = memberMapper.analysis(startTime + " 16:00:00", endTime + " 17:00:00");
        Integer sumi = memberMapper.findCountByTime(startTime +" 17:00:00");

        Integer addSumj = memberMapper.analysis(startTime + " 17:00:00", endTime + " 18:00:00");
        Integer sumj = memberMapper.findCountByTime(startTime +" 18:00:00");

        Integer addSumk = memberMapper.analysis(startTime + " 18:00:00", endTime + " 29:00:00");
        Integer sumk = memberMapper.findCountByTime(startTime +" 29:00:00");

        Integer addSuml = memberMapper.analysis(startTime + " 19:00:00", endTime + " 20:00:00");
        Integer suml = memberMapper.findCountByTime(startTime +" 20:00:00");

        Integer addSumm = memberMapper.analysis(startTime + " 20:00:00", endTime + " 21:00:00");
        Integer summ = memberMapper.findCountByTime(startTime +" 21:00:00");

        Integer addSumn = memberMapper.analysis(startTime + " 21:00:00", endTime + " 22:00:00");
        Integer sumn = memberMapper.findCountByTime(startTime +" 22:00:00");

        Integer addSumo = memberMapper.analysis(startTime + " 22:00:00", endTime + " 23:00:00");
        Integer sumo = memberMapper.findCountByTime(startTime +" 23:00:00");

        Integer addSump = memberMapper.analysis(startTime + " 23:00:00", endTime + " 24:00:00");
        Integer sump = memberMapper.findCountByTime(startTime +" 24:00:00");

        ArrayList<Integer> addSumList = new ArrayList<>();
        addSumList.add(addSum);
        addSumList.add(addSum2);
        addSumList.add(addSum3);
        addSumList.add(addSum4);
        addSumList.add(addSum5);
        addSumList.add(addSum6);
        addSumList.add(addSum7);
        addSumList.add(addSum8);
        addSumList.add(addSum9);
        addSumList.add(addSuma);
        addSumList.add(addSumb);
        addSumList.add(addSumc);
        addSumList.add(addSumd);
        addSumList.add(addSume);
        addSumList.add(addSumf);
        addSumList.add(addSumg);
        addSumList.add(addSumh);
        addSumList.add(addSumi);
        addSumList.add(addSumj);
        addSumList.add(addSumk);
        addSumList.add(addSuml);
        addSumList.add(addSumm);
        addSumList.add(addSumn);
        addSumList.add(addSumo);
        addSumList.add(addSump);
        HashMap<String, Object> map = new HashMap<>();
        map.put("addSumList",addSumList);

        ArrayList<Integer> sumList = new ArrayList<>();
        sumList.add(sum);
        sumList.add(sum2);
        sumList.add(sum3);
        sumList.add(sum4);
        sumList.add(sum5);
        sumList.add(sum6);
        sumList.add(sum7);
        sumList.add(sum8);
        sumList.add(sum9);
        sumList.add(suma);
        sumList.add(sumb);
        sumList.add(sumc);
        sumList.add(sumd);
        sumList.add(sume);
        sumList.add(sumf);
        sumList.add(sumg);
        sumList.add(sumh);
        sumList.add(sumi);
        sumList.add(sumj);
        sumList.add(sumk);
        sumList.add(suml);
        sumList.add(summ);
        sumList.add(sumn);
        sumList.add(sumo);
        sumList.add(sump);
        map.put("sumList",sumList);
        return map;
    }
}