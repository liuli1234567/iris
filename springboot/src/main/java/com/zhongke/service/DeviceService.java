package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.Device;

public interface DeviceService {
    /**
     * @Description 查询设备列表
     * @author 一只逆袭的程序猿
     * @date 2020/3/31 14:52
     * @param device
     * @return java.util.List<java.util.Map>
     **/
    PageInfo<Device> devices(Device device, int page, int size);
}
