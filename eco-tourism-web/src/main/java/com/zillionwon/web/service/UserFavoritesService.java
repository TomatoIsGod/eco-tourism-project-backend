package com.zillionwon.web.service;


import com.zillionwon.web.constant.FavoriteType;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.SightOverview;
import com.zillionwon.web.mapper.UserFavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用戶收藏接口
 *
 * @author CH
 */

@Service
public class UserFavoritesService {

    @Autowired
    private UserFavoritesMapper userFavoritesMapper;

    /**
     * 获取用户的收藏列表
     *
     * @param userId 用户ID
     * @return 用户的收藏列表
     */
    public List<SightOverview> getFavoriteSights(Long userId) {
        return userFavoritesMapper.findSightsByUserId(userId);
    }

    public SightOverview getFavoriteSight(Long userId, Long sightId) {
        return userFavoritesMapper.findSightByObjectId(userId, sightId);
    }

    /**
     * 添加新的收藏
     *
     * @param userId  用户ID
     * @param sightId 收藏内容
     */
    public void addFavoriteSight(Long userId, Long sightId) {

        userFavoritesMapper.insert(userId, FavoriteType.SIGHT.getCode(), sightId);
    }

    /**
     * 删除收藏
     *
     * @param userId  用户ID
     * @param sightId 收藏内容
     */
    public void deleteFavoriteSight(Long userId, Long sightId) {
        userFavoritesMapper.delete(userId, FavoriteType.SIGHT.getCode(), sightId);
    }


    public List<City> getFavoriteCities(Long userId) {
        return userFavoritesMapper.findCitiesByUserId(userId, FavoriteType.CITY.getCode());
    }

    public City getFavoriteCity(Long userId, Long cityId) {
        return userFavoritesMapper.findCityByObjectId(userId, cityId, FavoriteType.CITY.getCode());
    }

    /**
     * 添加新的城市收藏
     *
     * @param userId 用户ID
     * @param cityId 收藏城市Id
     */
    public void addFavoriteCity(Long userId, Long cityId) {

        userFavoritesMapper.insert(userId, FavoriteType.CITY.getCode(), cityId);
    }

    /**
     * 删除城市收藏
     *
     * @param userId 用户ID
     * @param cityId 收藏城市Id
     */
    public void deleteFavoriteCity(Long userId, Long cityId) {
        userFavoritesMapper.delete(userId, FavoriteType.CITY.getCode(), cityId);
    }
}
