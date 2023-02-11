package com.example.demo.dto;

import org.springframework.util.StringUtils;

/**
 * 分页所需参数
 */
public class RequestPage {
    private Integer page;
    private Integer size;

    /**
     * 页码，为非必传参数，默认值为 1
     */
    public Integer getPage() {
        return StringUtils.isEmpty(page) ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 大小，非必传参数，默认值为 10
     */
    public Integer getSize() {
        return StringUtils.isEmpty(size) ? 10 : size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
