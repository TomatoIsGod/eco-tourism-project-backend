package com.zillionwon.spider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 翻页处理器
 * @author InwardFlow
 */
public class Page<T extends BaseField> implements Serializable {
    private Boolean hasNext;

    public Boolean hasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    private String url;
    private List<T> data = new ArrayList<>();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void add(T data) {
        this.data.add(data);
    }

    public Page(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
