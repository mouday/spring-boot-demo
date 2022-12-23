package com.example.demo.utils;

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
}