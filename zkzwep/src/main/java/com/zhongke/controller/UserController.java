package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.JwtUtil;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
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

    /**
     * @Description 后台系统登录
     * @author liuli
     * @date 2020/5/21 17:41
    // * @param u 登录对象
     * @return com.zhongke.entity.Result
     **/
    @PostMapping("/login")
    public Result login(@RequestBody User u) {
        User user = userService.login(u);
        if (user != null) {
            String token = new JwtUtil().createJWT(user.getUsername(), user.getUsername());
            System.out.println(token);
            user.setPassword(null);
            user.setToken(token);
            return new Result(StatusCode.SUCCESS,"登录成功",user);
        }else {
            return new Result(StatusCode.FALL,"登录失败，用户名或密码错误");
        }
    }

    /**
     * @Description 添加系统登录用户
     * @author liuli
     * @date 2020/5/21 17:50
     * @param username 用户名
     * @param password 密码
     * @param role 角色
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/add")
    public Result add(@RequestParam String username,@RequestParam String password,@RequestParam int role){
        int flag = userService.add(username, password, role);
        if (-1 == flag){
            return new Result(StatusCode.FALL,"注册失败，用户名已存在");
        }
        return new Result(StatusCode.SUCCESS,"添加成功");
    }

    /**
     * @Description 查询后台系统用户列表
     * @author liuli
     * @date 2020/5/21 17:41
     * @param nameOrPhone 姓名或手机号
     * @param role 角色
     * @param page 当前页
     * @param size 每页显示条数
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(required = false) String nameOrPhone,@RequestParam(required = false)Integer role,
                          @RequestParam int page,@RequestParam int size){
        PageInfo<User> userPageInfo = userService.findAll(nameOrPhone,role,page,size);
        return new Result(StatusCode.SUCCESS,"查询成功",userPageInfo);
    }

    /**
     * @Description 根据id删除用户
     * @author liuli
     * @date 2020/5/21 17:57
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/deleteById")
    public Result delete(@RequestParam int id){
        userService.deleteById(id);
        return new Result(StatusCode.SUCCESS,"删除成功");
    }

    /**
     * @Description 根据id更新用户
     * @author liuli
     * @date 2020/5/21 18:03
     * @param user 用户对象
     * @return com.zhongke.entity.Result
     **/
    @PutMapping("/updateById")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        return new Result(StatusCode.SUCCESS,"编辑成功");
    }
}
