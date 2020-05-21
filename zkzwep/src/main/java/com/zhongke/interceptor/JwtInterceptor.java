package com.zhongke.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongke.entity.JwtUtil;
import com.zhongke.entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName JwtInterceptor
 * @Description
 * @Author liuli
 * @Date 2020/5/20 16:54
 * @Version 1.0
 **/
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    //每个方法都验证token
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器!");
        // 2.1 从请求参数获取token
        String token = request.getParameter("AUTHORIZE_TOKEN");
        // 2.2 从请求头获取token
        if (StringUtils.isEmpty(token)){
            token = request.getHeader("AUTHORIZE_TOKEN");
        }
        if (StringUtils.isEmpty(token)) {
            Result result = new Result<>(-2,"未登录");
            String jsonString = JSON.toJSONString(result);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(jsonString);
            return false;
        }
        try {
            Claims claims = jwtUtil.parseJWT(token);
        } catch (Exception e) {
            Result result = new Result<>(-3,"token错误！");
            String jsonString = JSON.toJSONString(result);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(jsonString);
            return false;
        }
        /*if (claims != null) {
            if("1".equals(claims.get("roles"))){//如果是
                request.setAttribute("1_claims", claims);
            }
            if("2".equals(claims.get("roles"))){//如果是用户
                request.setAttribute("2_claims", claims);
            }
        }*/
        return true;
    }
}
