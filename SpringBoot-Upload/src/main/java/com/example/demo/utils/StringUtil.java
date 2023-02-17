package com.example.demo.utils;

import java.util.UUID;

/**
 * 字符串处理工具类
 */
public class StringUtil {
    /**
     * 获取文件名后缀，带有.
     *
     * @param filename
     * @return
     */
    public static String getFilenameSuffixWithDot(String filename) {
        if (filename == null) {
            return null;
        }

        return filename.substring(filename.lastIndexOf("."));
    }

    public static String getRandomFilename(String suffixWithDot) {
        // 使用uuid生成文件名，防止文件名重复造成文件覆盖
        return UUID.randomUUID().toString() + suffixWithDot;
    }
}