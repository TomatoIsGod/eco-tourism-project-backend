package com.zillionwon.web.controller;

import com.zillionwon.web.domain.R;
import com.zillionwon.web.domain.UserFavorites;
import com.zillionwon.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/UserFavorites")
public class UserFavoritesController {

    private  final UserFavoritesService userFavoritesService;
    @Autowired
    public UserFavoritesController(UserFavoritesService userFavoritesService) {
        this.userFavoritesService = userFavoritesService;
    }


    /**
     * 获取用户的收藏列表
     */
    @GetMapping("/favorites")
    public R<List<String>> getUserFavorites(@RequestParam Long userId) {
        List<String> favorites = userFavoritesService.getUserFavorites(userId);
        return R.ok("获取成功", favorites);
    }

    /**
     * 搜索用户的收藏列表
     */
    @GetMapping("/search")
    public R<List<String>> searchUserFavorite(@RequestParam Long userId, @RequestParam String favorite) {
        List<String> matchedFavorites = userFavoritesService.searchUserFavorite(userId, favorite);
        return R.ok("搜索成功", matchedFavorites);
    }

    /**
     * 添加新的收藏
     */
    @PostMapping("/add")
    public R<String> addUserFavorite(@RequestBody UserFavorites userFavorites) {
        userFavoritesService.addUserFavorite(userFavorites.getUserId(), userFavorites.getFavoriteContent());
        return R.ok("添加成功", userFavorites.getFavoriteContent());
    }

    /**
     * 删除收藏
     */
    @DeleteMapping("/delete")
    public R<String> deleteUserFavorite(@RequestBody UserFavorites userFavorites) {
        userFavoritesService.deleteUserFavorite(userFavorites.getUserId(), userFavorites.getFavoriteContent());
        return R.ok("删除成功", userFavorites.getFavoriteContent());
    }

}