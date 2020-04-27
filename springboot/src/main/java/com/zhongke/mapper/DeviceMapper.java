package com.zhongke.mapper;

import com.zhongke.pojo.Device;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DeviceMapper extends Mapper<Device> {

    List<Device> findDevicesByStoreId(int storeId);

    Integer findDeviceIdByDeviceNo(String deviceNo);

}
