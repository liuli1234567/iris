package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Member;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.service.MemberService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName MemberController
 * @Description 会员
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired(required = false)
    private MemberService memberService;

    /**
     * @Description 根据条件查询并分页
     * @author liuli
     * @date 2020/4/6 11:17
     * @param member
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo>
     **/
    @PostMapping("/members/{page}/{size}")
    public Result<PageInfo> memGrades(@RequestBody(required = false) Member member, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Member> members = memberService.memGrades(member,page,size);
            return new Result<>(0,"查询成功",members);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.memGrades(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    /**
     * @Description 查询会员基本信息
     * @author liuli
     * @date 2020/4/5 22:48
     * @param id
     * @return com.zhongke.entity.Result<com.zhongke.pojo.Member>
     **/
    @PostMapping("/findById/{id}")
    public Result<Member> findById(@PathVariable int id){
        try {
            Member member = memberService.findById(id);
            return new Result<Member>(0,"查询成功",member);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.findById(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody Member member,@PathVariable int id){
        try {
            member.setId(id);
            memberService.update(member);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }

    }

    /**
     * @Description 统计时间段的新增会员数和会员总数
     * @author liuli
     * @date 2020/4/6 11:17
     * @param startTime
     * @param endTime
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/analysis")
    public Result analysis(@RequestParam String startTime,@RequestParam String endTime){
        try {
            Map map = memberService.analysis(startTime+" 00:00:00",endTime+" 23:59:59");
            return new Result<>(0,"查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.analysis(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @GetMapping("/analysisByTime")
    public Result analysisByTime(@RequestParam String startTime, @RequestParam String endTime){
        try {
            Map map = memberService.analysisByTime(startTime,endTime);
            return new Result<>(0,"查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.analysisByTime(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }
}