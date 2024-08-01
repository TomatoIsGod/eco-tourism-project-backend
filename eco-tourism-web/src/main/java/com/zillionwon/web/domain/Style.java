package com.zillionwon.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 风格实体类
 *
 *
 */
@TableName("style")
public class Style {
    private long StyleId;
    private String StyleName;

    // getters and setters
    public Long getStyleId() {
        return StyleId;
    }

    public void setStyleId(Long StyleId) {
        this.StyleId = StyleId;
    }

    public String getStyleName() {
        return StyleName;
    }

    public void setStyleName(String StyleName) {
        this.StyleName = StyleName;
    }
}

