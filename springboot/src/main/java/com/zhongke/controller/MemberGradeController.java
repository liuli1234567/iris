package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Jam;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.service.JamService;
import com.zhongke.service.MemberGradeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

/**
 * @ClassName MemberGradeController
 * @Description 会员等级
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员等级相关")
@RestController
@RequestMapping("/memGrade")
public class MemberGradeController {

    private final Logger log = LoggerFactory.getLogger(MemberGradeController.class);

    @Autowired(required = false)
    private MemberGradeService memberGradeService;

    @PostMapping("/memGrades/{page}/{size}")
    public Result<PageInfo> memGrades(@RequestBody(required = false) MemberGrade memberGrade, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<MemberGrade> memGrades = memberGradeService.memGrades(memberGrade,page,size);
            return new Result<>(0,"查询成功",memGrades);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberGradeController.memGrades(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody MemberGrade memberGrade,@PathVariable int id){
        try {
            memberGrade.setId(id);
            memberGradeService.update(memberGrade);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberGradeController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody MemberGrade memberGrade){
        try {
            memberGradeService.add(memberGrade);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberGradeController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        try {
            memberGradeService.delete(id);
            return new Result<>(0,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberGradeController.delete(): "+e.getMessage());
            return new Result<>(-1,"删除失败");
        }
    }
}