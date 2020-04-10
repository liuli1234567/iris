package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Jam;
import com.zhongke.pojo.SpuCategory;
import com.zhongke.service.JamService;
import com.zhongke.service.SpuCategoryService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SpuCategoryController
 * @Description 商品分类
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "商品分类相关")
@RestController
@RequestMapping("/spuCategory")
public class SpuCategoryController {

    private final Logger log = LoggerFactory.getLogger(SpuCategoryController.class);

    @Autowired(required = false)
    private SpuCategoryService spuCategoryService;

    @GetMapping("/spuCategorys/{page}/{size}")
    public Result<PageInfo> spuCategorys(@PathVariable int page, @PathVariable int size){
        try {
            PageInfo<SpuCategory> spuCategorys = spuCategoryService.spuCategorys(page,size);
            return new Result<>(0,"查询成功",spuCategorys);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuCategoryController.spuCategorys(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody() SpuCategory spuCategory){
        try {
            spuCategoryService.add(spuCategory);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuCategoryController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        try {
            spuCategoryService.delete(id);
            return new Result<>(0,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuCategoryController.delete(): "+e.getMessage());
            return new Result<>(-1,"删除失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() SpuCategory spuCategory,@PathVariable int id){
        try {
            spuCategory.setId(id);
            spuCategoryService.update(spuCategory);
            return new Result<>(0,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SpuCategoryController.update(): "+e.getMessage());
            return new Result<>(-1,"修改失败");
        }
    }
}