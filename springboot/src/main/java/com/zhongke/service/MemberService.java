package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Member;

import java.util.Date;
import java.util.Map;

public interface MemberService {

    PageInfo<Member> memGrades(Member member, int page, int size);

    /**
     * @Description 查询会员基本信息
     * @author 一只逆袭的程序猿
     * @date 2020/4/5 22:47
     * @param id
     * @return com.zhongke.pojo.Member
     **/
    Member findById(int id);

    void update(Member member);

    Map analysis(String startTime, String endTime);

    Map analysisByTime(String startTime, String endTime);
}
