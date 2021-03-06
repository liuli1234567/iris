package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.POIUtils;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Order;
import com.zhongke.pojo.Spu;
import com.zhongke.pojo.SpuCategory;
import com.zhongke.service.SpuCategoryService;
import com.zhongke.service.SpuService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SpuController
 * @Description 商品
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "商品相关")
@RestController
@RequestMapping("/spu")
public class SpuController {

    private final Logger log = LoggerFactory.getLogger(SpuController.class);

    @Autowired(required = false)
    private SpuService spuService;

    /**
     * @Description 商品批量导入
     * @author liuli
     * @date 2020/5/16 9:46
     * @param excelFile
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/import_spu")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            if (list != null && list.size() > 0){
                List<Spu> spus = new ArrayList<>();
                for (String[] strings : list) {
                    Spu spu = new Spu();
                    spu.setName(strings[0]);
                    spu.setNo(strings[1]);
                    spu.setPrice(new BigDecimal(strings[2]));
                    spu.setSn(strings[3]);
                    spu.setCategoryName(strings[4]);
                    spu.setNum(Integer.parseInt(strings[5]));
                    spus.add(spu);
                }
                spuService.addAll(spus);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(-1,"导入失败",e.getMessage());
        }
        return new Result(0,"批量导入成功");
    }

    @GetMapping("/spus/{page}/{size}")
    public Result<PageInfo> spus(@RequestParam(required = false)String nameOrNo,@RequestParam(required = false)int isMarketable,
                                 @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Spu> spus = spuService.spus(nameOrNo,isMarketable,page,size);
            return new Result<>(0,"查询成功",spus);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuController.spus():{}, ",e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody() Spu spu){
        try {
            spuService.add(spu);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuController.add():{}, ",e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        try {
            spuService.delete(id);
            return new Result<>(0,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuController.delete(): "+e.getMessage());
            return new Result<>(-1,"删除失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() Spu spu,@PathVariable long id){
        try {
            spu.setId(id);
            spuService.update(spu);
            return new Result<>(0,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuController.update(): "+e.getMessage());
            return new Result<>(-1,"修改失败");
        }
    }

    /**
     * @Description 批量操作：上下架，删除
     * @author liuli
     * @date 2020/4/9 14:48
     * @param spuIds
     * @param status
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/batch")
    public Result batch(@RequestParam() String spuIds,@RequestParam int status){
        try {
            spuService.batch(spuIds,status);
            return new Result<>(0,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuController.batch(): "+e.getMessage());
            return new Result<>(-1,"操作失败");
        }
    }
}