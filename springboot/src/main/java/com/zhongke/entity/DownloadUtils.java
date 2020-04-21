package com.zhongke.entity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName DownloadUtils
 * @Description
 * @Author liuli
 * @Date 2020/4/20 16:09
 * @Version 1.0
 **/
public class DownloadUtils {
    public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response,String returnName) throws IOException {
        response.setContentType("application/octet-stream");
        returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1")); // 保存的文件名，必须和页面编码一致
        response.addHeader("content-disposition","attachment;filename="+returnName);
        response.setContentLength(byteArrayOutputStream.size());
        ServletOutputStream outputStream = response.getOutputStream(); // 取得输出流
        byteArrayOutputStream.writeTo(outputStream); // 写到输出流
        byteArrayOutputStream.close(); // 关闭
        outputStream.flush(); // 刷数据
    }
}
