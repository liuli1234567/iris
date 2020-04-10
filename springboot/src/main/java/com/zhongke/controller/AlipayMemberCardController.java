package com.zhongke.controller;

import com.zhongke.entity.Result;
import com.zhongke.pojo.AlipayMemberCard;
import com.zhongke.pojo.MemberCard;
import com.zhongke.service.AlipayMemberCardService;
import com.zhongke.service.MemberCardService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AlipayMemberCardController
 * @Description 支付宝会员卡
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "支付宝会员卡相关")
@RestController
@RequestMapping("/alipayMemCard")
public class AlipayMemberCardController {

    private final Logger log = LoggerFactory.getLogger(AlipayMemberCardController.class);

    @Autowired(required = false)
    private AlipayMemberCardService alipayMemberCardService;

    @GetMapping("/find")
    public Result<List<AlipayMemberCard>> find(){
        try {
            List<AlipayMemberCard> alipayMemberCards = alipayMemberCardService.find();
            return new Result<>(0,"查询成功",alipayMemberCards);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AlipayMemberCardController.find(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody() AlipayMemberCard alipayMemberCard){
        try {
            alipayMemberCardService.add(alipayMemberCard);
            return new Result<>(0,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AlipayMemberCardController.add(): "+e.getMessage());
            return new Result<>(-1,"新增失败");
        }
    }

}