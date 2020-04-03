package com.zhongke.mapper;

import com.zhongke.pojo.Announcement;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnnouncementMapper extends Mapper<Announcement> {

    /**
     * @Description 查询最新公告
     * @author liuli
     * @date 2020/4/2 14:27
     * @param
     * @return com.zhongke.pojo.Announcement
     **/
    Announcement findOneAnnouByTime(@Param("merchantId") int merchantId);

    /**
     * @Description 根据条件查询公告集合并分页
     * @author liuli
     * @date 2020/4/2 15:56
     * @param title
     * @param startTime
     * @param endTime
     * @return java.util.List<com.zhongke.pojo.Announcement>
     **/
    List<Announcement> announs(@Param("title") String title, @Param("startTime") String startTime, @Param("endTime") String endTime);

}
