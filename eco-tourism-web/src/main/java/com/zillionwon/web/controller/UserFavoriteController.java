package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.common.satoken.util.LoginHelper;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.SightOverview;
import com.zillionwon.web.service.UserFavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户收藏的景点夹接口
 *
 * @author black
 */

@Slf4j
@RestController
@RequestMapping("/user/favorite")
public class UserFavoriteController {

    private final UserFavoritesService userFavoritesService;

    @Autowired
    public UserFavoriteController(UserFavoritesService userFavoritesService) {
        this.userFavoritesService = userFavoritesService;
    }

    /**
     * 获取用户的收藏景点列表
     */
    @GetMapping
    public R<List<SightOverview>> getFavoriteSights() {
        Long userId = LoginHelper.getUserId();
        try {
            List<SightOverview> favoriteSights = userFavoritesService.getFavoriteSights(userId);
            return R.ok("获取成功", favoriteSights);
        } catch (NullPointerException e) {
            log.info("userId 为 {} 的用户不存在", userId);
            return R.fail("未找到该用户的收藏");
        }
    }

    @PostMapping
    public R<SightOverview> addFavoriteSight(Long sightId) {
        Long userId = LoginHelper.getUserId();
        SightOverview sight = userFavoritesService.getFavoriteSight(userId, sightId);
        if (sight != null) {
            return R.fail("景点已存在");
        }

        userFavoritesService.addFavoriteSight(userId, sightId);
        return R.ok("添加收藏成功");
    }

    // TODO: 景点的详细页面

    /**
     * 删除收藏的景点
     * @param sightId 景点ID
     * @return 是否成功
     */
    @DeleteMapping
    public R<Void> deleteSightFavorite(Long sightId) {
        Long userId = LoginHelper.getUserId();
        SightOverview sight = userFavoritesService.getFavoriteSight(userId, sightId);
        if (sight != null) {
            userFavoritesService.deleteFavoriteSight(userId, sightId);
            return R.ok();
        }
        return R.fail("景点本身不存在该用户收藏夹中");
    }

    /**
     * 获取用户的收藏城市列表
     * @return 城市列表
     */
    @GetMapping("/getFavoriteCities")
    public R<List<City>> getFavoriteCities() {
        Long userId = LoginHelper.getUserId();
        try {
            List<City> favoriteCityies = userFavoritesService.getFavoriteCities(userId);
            return R.ok("获取成功", favoriteCityies);
        } catch (NullPointerException e) {
            log.info("userId 为 {} 的用户不存在", userId);
            return R.fail("未找到该用户收藏的城市");
        }
    }

    /**
     * 添加新的收藏城市
     * @param cityId 城市ID
     * @return 城市列表
     */
    @PostMapping("/addFavoriteCity")
    public R<City> addFavoriteCity(Long cityId) {
        Long userId = LoginHelper.getUserId();
        City city = userFavoritesService.getFavoriteCity(userId, cityId);
        if (city != null) {
            return R.fail("当前城市已被收藏过");
        }

        userFavoritesService.addFavoriteCity(userId, cityId);
        return R.ok("添加收藏成功");
    }

    /**
     * 删除收藏的城市
     * @param cityId 城市ID
     * @return 是否成功
     */
    @DeleteMapping("/deleteFavoriteCity")
    public R<Void> deleteFavoriteCity(Long cityId) {
        Long userId = LoginHelper.getUserId();
        City city = userFavoritesService.getFavoriteCity(userId, cityId);
        if (city != null) {
            userFavoritesService.deleteFavoriteCity(userId, cityId);
            return R.ok();
        }
        return R.fail("当前城市不存在于该用户收藏夹中");
    }

}
