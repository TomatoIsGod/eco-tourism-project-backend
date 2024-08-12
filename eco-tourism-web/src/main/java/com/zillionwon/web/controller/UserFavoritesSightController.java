package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.Sight;
import com.zillionwon.web.domain.Style;
import com.zillionwon.web.domain.UserFavoritesSight;
import com.zillionwon.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 用户收藏的景点夹接口
 *
 * @author black
 */

@Slf4j
@RestController
@RequestMapping("/UserFavoritesSight")
public class UserFavoritesSightController {

    private  final UserFavoritesSightService userFavoritesSightService;
    @Autowired
    public UserFavoritesSightController(UserFavoritesSightService userFavoritesSightService) {
        this.userFavoritesSightService = userFavoritesSightService;
    }


    /**
     * 获取用户的收藏景点列表
     */
    @GetMapping("/favoritesSight")
    public R<List<Sight>> getUserFavorites(@RequestParam Long userId) {
        try{List<Sight> favorites_sight = userFavoritesSightService.getUserFavoritesSight(userId);
        return R.ok("获取成功", favorites_sight);}catch (NullPointerException e){
            log.info("userId 为 {} 的用户不存在", userId);
            return R.fail("未找到该用户的收藏");
        }
    }

    /**
     * 添加新的收藏景点
     */
    @PostMapping
    public R<Sight> addUserFavoriteSight(@RequestParam Long userId,  Sight userFavoritesSight) {
        List<Sight> favorites_sight = userFavoritesSightService.getUserFavoritesSight(userId);
        for (Sight sight : favorites_sight) {
            if (sight.getName().equals(userFavoritesSight.getName()) ){
                return R.fail("景点已存在");
            }
        }
        userFavoritesSightService.addUserFavoriteSight(userId, userFavoritesSight.getName());
        return R.ok("添加成功", userFavoritesSight);
    }
    
    /**
     * 景点的详细页面
     */

    /**
     * 删除收藏
     */
    @DeleteMapping
    public R<Sight> deleteUserFavoriteSight(@RequestParam Long userId,  Sight userFavoritesSight) {
        List<Sight> favorites_sight = userFavoritesSightService.getUserFavoritesSight(userId);
        for (Sight sight : favorites_sight) {
            if (sight.getName().equals(userFavoritesSight.getName())) {
                userFavoritesSightService.deleteUserFavoriteSight(userId,userFavoritesSight.getName());
                return R.ok();
            }
        }
        return R.fail("景点本身不存在该用户收藏夹中");
    }

}
