package com.zhongke.mapper;

import com.zhongke.pojo.Member;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


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
}
