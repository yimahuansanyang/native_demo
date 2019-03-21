package com.example.zhangdachun.mytestdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    public static String parseSteam(InputStream inputStream) {
        try {
            // 定义一个字节数组输出流
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            // 定义一个字节数组
            byte[] buffer = new byte[1024];
            // 定义初始长度
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                // 将读的内容，写到字节数组输出流中
                arrayOutputStream.write(buffer, 0, len);
            }
            // 将字节输出流转成字符串
            return arrayOutputStream.toString("utf-8");
            // utf-8 大小写都可以，gbk 必须大写
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
