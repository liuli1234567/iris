package com.zhongke.controller;

import com.zhongke.entity.Result;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.PlatformUserService;
import com.zhongke.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description 登录
 * @Author liuli
 * @Date 2020/4/28 11:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired(required = false)
    private UserLoginService userLoginService;

    @RequestMapping("/login")
    public Result login(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password){
        PlatformUser platformUser = userLoginService.login(username,password);
        if (platformUser != null) {
            return new Result(0,"登录成功！");
        }else {
            return new Result(-1,"登录失败,用户名或密码错误");
        }
    }
}
