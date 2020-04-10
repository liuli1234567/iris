package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Jam;
import com.zhongke.pojo.MemberCard;
import com.zhongke.service.JamService;
import com.zhongke.service.MemberCardService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MemberCardController
 * @Description 商户会员卡相关
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "商户会员卡相关")
@RestController
@RequestMapping("/memberCard")
public class MemberCardController {

    private final Logger log = LoggerFactory.getLogger(MemberCardController.class);

    @Autowired(required = false)
    private MemberCardService memberCardService;

    @GetMapping("/find")
    public Result<List<MemberCard>> find(){
        try {
            List<MemberCard> memberCards = memberCardService.find();
            return new Result<>(0,"查询成功",memberCards);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberCardController.find(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody() MemberCard memberCard){
        try {
            memberCardService.add(memberCard);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberCardController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody() MemberCard memberCard,@PathVariable int id){
        try {
            memberCard.setId(id);
            memberCardService.update(memberCard);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberCardController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }
    }
}