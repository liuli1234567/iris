package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Device;

import java.util.Map;

public interface DeviceService {
    /**
     * @Description 查询设备列表
     * @author 一只逆袭的程序猿
     * @date 2020/3/31 14:52
     * @param device
     * @return java.util.List<java.util.Map>
     **/
    PageInfo<Device> devices(Device device, int page, int size);

    void add(Device device);

    /**
     * @Description 设备列表设备号查看流水统计
     * @author liuli
     * @date 2020/5/15 15:55
     * @param device_no
     * @return java.util.Map
     **/
    Map transaction_count(String device_no);
}
