package com.zhongke.mapper;

import com.zhongke.pojo.Member;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface MemberMapper extends Mapper<Member> {
    /**
     * @Description 统计时间段的新增会员数
     * @author 一只逆袭的程序猿
     * @date 2020/4/6 11:16
     * @param startTime
     * @param endTime
     * @return int
     **/
    Integer analysis(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Integer findCountByTime(String time);

    /**
     * @Description 会员列表导出
     * @author liuli
     * @date 2020/4/20 11:09
     * @param member
     * @return java.util.List<com.zhongke.pojo.Member>
     **/
    List<Member> exportMembers(Member member);
}
