package com.zhongke.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Description 文件上传
 * @Author liuli
 * @Date 2020/4/20 17:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/uploadImage")
    public Result upload(@RequestParam(required = false) MultipartFile file,
                         HttpServletRequest request) {
        String path = null;// 文件路径
        if (file != null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            logger.info("上传的文件原名称:{}",fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    //String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath = null;
                    try {
                        realPath = ResourceUtils.getURL("classpath:").getPath();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 自定义的文件名称
                    String trueFileName = DateUtil.getTime() + "." + type;
                    // 设置存放图片文件的路径
                    //path = realPath +/*System.getProperty("file.separator")+*/trueFileName;
                    //path = "E:\\IdeaProjects\\springboot\\src\\main\\resources\\static\\images\\" + trueFileName;
                    path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\" + trueFileName;
                    File directory = new File(path);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    logger.info("存放图片文件的路径:{}",path);
                    // 转存文件到指定的路径
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文件成功上传到指定目录下");
                    logger.info("文件成功上传到指定目录下");
                }
                return new Result(0,"文件上传成功！",path);
            } else {
                logger.info("不是我们想要的文件类型,请按要求重新上传");
                return new Result(-1, "不是我们想要的文件类型,请按要求重新上传");
            }
        } else {
            System.out.println("文件类型为空");
            logger.info("文件类型为空");
            return new Result(-1, "文件类型为空");
        }
    }

    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }
}
