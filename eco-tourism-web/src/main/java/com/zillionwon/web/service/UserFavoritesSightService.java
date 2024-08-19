package com.zillionwon.web.service;


import com.zillionwon.web.domain.Sight;
import com.zillionwon.web.mapper.UserFavoritesSightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoritesSightService {

    @Autowired
    private UserFavoritesSightMapper userFavoritesSightMapper;


    /**
     * 获取用户的收藏列表
     *
     * @param userId 用户ID
     * @return 用户的收藏列表
     */

    public List<Sight> getUserFavoritesSight(Long userId) {
        return userFavoritesSightMapper.findByUserId(userId);
    }


    /**
     * 添加新的收藏
     * @param userId 用户ID
     * @param favoriteSight 收藏内容
     */

    public void addUserFavoriteSight(Long userId, String favoriteSight) {

        userFavoritesSightMapper.insert(favoriteSight,userId);
    }

    /**
     * 删除收藏
     * @param userId 用户ID
     * @param favoriteSight 收藏内容
     */

    public void deleteUserFavoriteSight(Long userId, String favoriteSight) {

        userFavoritesSightMapper.delete(favoriteSight,userId);
    }
}