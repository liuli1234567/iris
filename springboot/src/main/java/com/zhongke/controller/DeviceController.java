package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Device;
import com.zhongke.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DeviceController
 * @Description 设备
 * @Author liuli
 * @Date 2020/3/31 14:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/device")
@Api(value = "设备相关")
public class DeviceController {

    private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired(required = false)
    private DeviceService deviceService;

    /**
     * @Description 查询设备列表接口
     * @author liuli
     * @date 2020/3/31 18:50
     * @param device 设备实体类
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result<java.util.List<com.zhongke.pojo.Device>>
     **/
    @ApiOperation(value = "获取设备列表",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device",value = "设备实体类",required = false,dataType = "Device",paramType = "body"),
            @ApiImplicitParam(name = "page",value = "当前页",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "size",value = "每页显示条数",required = true,dataType = "int",paramType = "path")
    })
    @PostMapping("/devices/{page}/{size}")
    public Result<List<Device>> devices(@RequestBody(required = false) Device device, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Device> devices = deviceService.devices(device,page,size);
            return new Result<>(0,"查询设备列表成功",devices);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DeviceController.devices(): "+e.getMessage());
            return new Result<>(-1,"查询设备列表失败:"+e.getMessage());
        }
    }

}
