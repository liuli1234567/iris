package com.zhongke.controller;

import com.zhongke.entity.BCrypt;
import com.zhongke.entity.DateUtil;
import com.zhongke.entity.Result;
import com.zhongke.entity.StatusCode;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FileController
 * @Description
 * @Author liuli
 * @Date 2020/5/19 11:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/contract_upload")
    public Result upload(@RequestParam(required = false) MultipartFile file,
                           HttpServletRequest request) {
        String path = null;// 文件路径
        if (file != null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            log.info("原文件名：" + fileName);
            String realPath = null;
            try {
                realPath = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\word\\" + fileName;
            System.out.println("path"+path);
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 转存文件到指定的路径
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("文件成功上传到指定目录下");
            System.out.println("https://zkzw8888.utools.club/word/"+fileName);
            return new Result(StatusCode.SUCCESS,"文件上传成功","https://zkzw8888.utools.club/word/"+fileName);
        } else {
            return new Result(StatusCode.FALL,"文件不能为空！");
        }
    }

    @GetMapping("/contract_download")
    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "熔喷布项目计划文档.docx"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream(getProjectRootPath() + "static/word/"+"熔喷布项目计划文档.docx");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 获取项目打包后在服务器的项目路径
     * @author liuli
     * @date 2020/5/29 9:55
     * @param
     * @return java.lang.String
     **/
    public String getProjectRootPath(){
        // 获取项目路径（兼容war服务和jar服务）
        String rootPath = this.getClass().getResource("/").getPath();
        String[] splits = rootPath.split("/");
        int breakI = 0;
        boolean isJarService = false;// 项目是否是打成jar包发布
        for (int i = 0; i < splits.length; i++) {
            if (splits[i].contains(".jar")){
                breakI = i;
                isJarService = true;
                break;
            }
        }
        String finalRootPath = "";
        if (isJarService){
            // 打成jar包后，获取的路径中会以file:XXX/XXX开头，所以去除切割后的第一个路径，i从1开始遍历
            for (int i = 1; i < breakI; i++) {
                finalRootPath += splits[i] + "/";
            }
        } else {
            finalRootPath = rootPath;
        }
        return finalRootPath;
    }

    @PostMapping(value = "/image_upload")
    public Result imageUpload(@RequestParam(required = false) MultipartFile file,
                         HttpServletRequest request) {
        String path = null;// 文件路径
        if (file != null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            log.info("上传的文件原名称:{}",fileName);
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
                    //path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\" + trueFileName;
                    path = getProjectRootPath()+"/static/images/" + trueFileName;
                    /*File directory = new File(path);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }*/
                    log.info("存放图片文件的路径:{}",path);
                    // 转存文件到指定的路径
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文件成功上传到指定目录下");
                    log.info("文件成功上传到指定目录下");
                }
                return new Result(StatusCode.SUCCESS,"文件上传成功！",path);
            } else {
                log.info("不是我们想要的文件类型,请按要求重新上传");
                return new Result(StatusCode.FALL, "不是我们想要的文件类型,请按要求重新上传");
            }
        } else {
            System.out.println("文件类型为空");
            log.info("文件类型为空");
            return new Result(StatusCode.FALL, "文件类型为空");
        }
    }


    // 生成token
    private static void createToken(){
        // 创建JWT
        JwtBuilder builder = Jwts.builder();
        // 构建头信息
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("keyId", "JWT");
        builder.setHeader(map);
        // 构建载荷信息
        builder.setId("001");
        builder.setIssuer("张三");
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis()+60000000)); // 30秒后过期
        // 声明自定义载荷信息
        Map<String,Object> claims = new HashMap<>();
        claims.put("school","北京人民大学");
        claims.put("address","三环");
        builder.setClaims(claims);
        // 添加签名
        builder.signWith(SignatureAlgorithm.HS256, "zkzw");
        // 生成token
        String token = builder.compact();
        System.out.println("token:"+token);
    }

    // 解析token
    public static void parseToken(){
        // 被解析的令牌
        String token = "eyJrZXlJZCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJhZGRyZXNzIjoi5LiJ546vIiwic2Nob29sIjoi5YyX5Lqs5Lq65rCR5aSn5a2mIn0.NJPURBX1VosDOcEcvyewFMgUm8Zxxp_2HTj1xEY1ssA";
        // 创建解析对象
        JwtParser parser = Jwts.parser();
        parser.setSigningKey("zkzw");
        System.out.println(parser.parseClaimsJws(token));
        Claims claims = parser.parseClaimsJws(token).getBody();
        System.out.println(claims);
    }
}
