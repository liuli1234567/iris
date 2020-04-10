package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Jam;
import com.zhongke.pojo.Store;
import com.zhongke.service.JamService;
import com.zhongke.service.StoreService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName StoreController
 * @Description 门店
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "门店相关")
@RestController
@RequestMapping("/store")
public class StoreController {

    private final Logger log = LoggerFactory.getLogger(StoreController.class);

    @Autowired(required = false)
    private StoreService storeService;

    @PostMapping("/stores/{page}/{size}")
    public Result<PageInfo> stores(@RequestBody(required = false) Store store, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Store> stores = storeService.stores(store,page,size);
            return new Result<>(0,"查询成功",stores);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("StoreController.stores(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody() Store store){
        try {
            storeService.add(store);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("StoreController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() Store store,@PathVariable int id){
        try {
            store.setId(id);
            storeService.update(store);
            return new Result<>(0,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("StoreController.update(): "+e.getMessage());
            return new Result<>(-1,"修改失败");
        }
    }

    @PostMapping("/findById/{id}")
    public Result<Store> findById(@PathVariable int id){
        try {
            Store store = storeService.findById(id);
            return new Result<>(0,"查询成功",store);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("StoreController.findById(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }
}