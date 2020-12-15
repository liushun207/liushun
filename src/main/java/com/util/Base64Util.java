package com.util;

import java.io.*;

/**
 * Base64Util
 * @author liushun
 * @date 2020/12/7
 */
public class Base64Util {


    private Base64Util() {

    }

    public static String videoToBase64(File videofilePath) {
        long size = videofilePath.length();
        byte[] imageByte = new byte[(int) size];
        FileInputStream fs = null;
        BufferedInputStream bis = null;
        try {
            fs = new FileInputStream(videofilePath);
            bis = new BufferedInputStream(fs);
            bis.read(imageByte);
        } catch(FileNotFoundException e) {
            // log.info("文件" + videofilePath.getName() + "不能被找到：{}", e.getMessage());
        } catch(IOException e) {
            // log.info("byte转换BASE64出错：" + e.getMessage());
        } finally {
            if(bis != null) {
                try {
                    bis.close();
                } catch(IOException e) {
                    // log.info("关闭输入流出错：" + e.getMessage());
                }
            }
            if(fs != null) {
                try {
                    fs.close();
                } catch(IOException e) {
                    // log.info("关闭输入流出错：" + e.getMessage());
                }
            }
        }
        return (new sun.misc.BASE64Encoder()).encode(imageByte);
    }
}
