package com.zhongke.config;

import com.alibaba.fastjson.JSON;
import com.zhongke.entity.JwtUtil;
import com.zhongke.entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName JwtInterceptor
 * @Description
 * @Author liuli
 * @Date 2020/5/28 10:07
 * @Version 1.0
 **/
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    //每个方法都验证token
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器!");
        final String token = request.getHeader("AUTHORIZE_TOKEN");
        if (StringUtils.isEmpty(token)) {
            Result result = new Result(-1, "未登陆");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.append(JSON.toJSONString(result));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return false;
        }
        Claims claims = null;
        try {
            claims = jwtUtil.parseJWT(token);
        } catch (Exception e) {
            Result result = new Result(-1, "用户名或密码错误");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.append(JSON.toJSONString(result));
            } catch (IOException e2) {
                e2.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return false;
        }
        return true;
    }
}
