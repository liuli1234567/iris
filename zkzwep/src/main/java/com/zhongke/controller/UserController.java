package com.zhongke.controller;

import com.zhongke.entity.JwtUtil;
import com.zhongke.entity.Result;
import com.zhongke.pojo.User;
import com.zhongke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User u) {
        User user = userService.login(u);
        if (user != null) {
            String token = new JwtUtil().createJWT(user.getUsername(), user.getUsername());
            System.out.println(token);
            user.setPassword(null);
            user.setToken(token);
            return new Result(0,"登录成功",user);
        }else {
            return new Result(-1,"登录失败");
        }
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        if(request.getAttribute("2_claims")==null){
            System.out.println("权限不足");
            return "false";
        }else {
            return "true";
        }
    }
}
