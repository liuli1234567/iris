package com.zhongke.mapper;

import com.zhongke.pojo.Product;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product>{
    /**
     * @Description 产品列表查询
     * @author liuli
     * @date 2020/5/22 10:20
     * @param name 产品名
     * @param inputStartTime 入库起始时间
     * @param inputEndTime 入库结束时间
     * @param outStartTime 出库起始时间
     * @param outEndTime 出库结束时间
     * @return java.util.List<com.zhongke.pojo.Product>
     **/
    List<Product> findAll(@Param("name") String name, @Param("inputStartTime") String inputStartTime, @Param("inputEndTime") String inputEndTime, @Param("outStartTime") String outStartTime, @Param("outEndTime") String outEndTime);
}
