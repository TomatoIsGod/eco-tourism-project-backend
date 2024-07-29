package com.zillionwon.web.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户收藏实体类
 *
 *
 */
@TableName("user_favorites")
public class UserFavorites {
    @TableId
    private Long userId;
    private String favoriteContent;

    // getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFavoriteContent() {
        return favoriteContent;
    }

    public void setFavoriteContent(String favoriteContent) {
        this.favoriteContent = favoriteContent;
    }
}