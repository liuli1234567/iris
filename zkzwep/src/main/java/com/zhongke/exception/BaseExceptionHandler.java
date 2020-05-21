package com.zhongke.exception;

import com.zhongke.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler
 * @Description 全局异常处理类
 * @Author liuli
 * @Date 2020/4/2 15:12
 * @Version 1.0
 **/
@ControllerAdvice
public class BaseExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    /**
     * @Description 统一异常处理方法
     * @author liuli
     * @date 2020/4/2 15:16
     * @param e
     * @return com.zhongke.entity.Result
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage());
        return new Result(-1,"系统忙，请稍后再试: ");
    }
}
