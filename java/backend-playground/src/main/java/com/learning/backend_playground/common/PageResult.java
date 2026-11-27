package com.learning.backend_playground.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageResult<T> {

    private List<T> items;

    private int pageIndex;

    private int pageSize;

    private int totalCount;

    private int totalPage;

    public static <T> PageResult<T> empty(final int pageIndex, final int pageSize){
        final PageResult<T> page = new PageResult<>();
        page.items = Collections.emptyList();
        page.pageIndex = pageIndex;
        page.pageSize = pageSize;
        page.totalCount = 0;
        page.totalPage = 0;
        return page;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(final List<T> items){
        this.items = items == null ? new ArrayList<>() : items;
    }

    public int getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(final int pageIndex){
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }
    
}
