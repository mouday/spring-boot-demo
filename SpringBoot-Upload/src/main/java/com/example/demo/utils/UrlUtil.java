package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * url工具类
 */
public class UrlUtil {
    /**
     * url 拼接
     * @param baseUrl
     * @param paths
     * @return
     */
    public static String urlJoin(String baseUrl, String... paths) {
        List<String> list = new ArrayList<>();

        list.add(UrlUtil.urlTrim(baseUrl));

        for (String path : paths) {
            list.add(UrlUtil.urlTrim(path));
        }

        return String.join("/", list);
    }

    public static String urlTrim(String url) {
        return urlTrimRight(urlTrimLeft(url));
    }

    public static String urlTrimLeft(String url) {
        if (url.startsWith("/")) {
            return url.substring(1);
        } else {
            return url;
        }
    }

    public static String urlTrimRight(String url) {
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        } else {
            return url;
        }
    }
}
