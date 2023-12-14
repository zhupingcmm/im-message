package com.ocbc.project.infrastructure.page;

import java.util.List;

public class PageObject<T>{
    private Long total;
    private Long pageIndex;

    private Long pageSize;

    private List<T> list;

    public void buildPage(Long pageIndex, Long pageSize, Long total, List<T> list) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
}
