package com.zillionwon.web.service;


import com.zillionwon.web.mapper.UserFavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoritesService {

    @Autowired
    private UserFavoritesMapper userFavoritesMapper;


    /**
     * 获取用户的收藏列表
     * @param userId 用户ID
     * @return 用户的收藏列表
     */

    public List<String> getUserFavorites(Long userId) {
        return userFavoritesMapper.findByUserId(userId);
    }

    /**
     * 获取用户的收藏列表
     * @param userId 用户ID
     * @return 用户的收藏列表
     */

    public List<String> searchUserFavorite(Long userId, String favoriteContent) {
        return userFavoritesMapper.findByFavoriteContent(favoriteContent,userId);
    }


    /**
     * 添加新的收藏
     * @param userId 用户ID
     * @param favoriteContent 收藏内容
     */

    public void addUserFavorite(Long userId, String favoriteContent) {

        userFavoritesMapper.insert(favoriteContent,userId);
    }

    /**
     * 删除收藏
     * @param userId 用户ID
     * @param favoriteContent 收藏内容
     */

    public void deleteUserFavorite(Long userId, String favoriteContent) {

        userFavoritesMapper.delete(favoriteContent,userId);
    }
}