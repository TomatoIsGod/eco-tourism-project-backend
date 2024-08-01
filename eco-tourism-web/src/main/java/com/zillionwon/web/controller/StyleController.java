package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.UserFavorites;
import com.zillionwon.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 旅行风格接口
 *
 * @author black
 */

@RestController
@RequestMapping("/style")
public class StyleController {
    private final StyleService StyleService;
    @Autowired
    public StyleController(StyleService styleService) {
        this.StyleService = styleService;
    }

    /**
     * 获取对应id的风格
     */
    @GetMapping("/style")
    public R<List<String>> getStyleId(@RequestParam Long styleId) {
        List<String> style = StyleService.getStyleId(styleId);
        return R.ok("获取成功", style);
    }

    /**
     * 搜索对应风格名称的风格
     */
    @GetMapping("/search")
    public R<List<String>> searchStyleName(@RequestParam String styleName) {
        List<String> matchedStyle = StyleService.searchStyleName(styleName);
        return R.ok("搜索成功", matchedStyle);
    }

    /**
     * 添加新的风格
     */
    @PostMapping("/add")
    public R<String> addStyle(@RequestBody UserFavorites userFavorites) {
        StyleService.addStyle(userFavorites.getUserId(), userFavorites.getFavoriteContent());
        return R.ok("添加成功", userFavorites.getFavoriteContent());
    }

    /**
     * 删除风格
     */
    @DeleteMapping("/delete")
    public R<String> deleteStyle(@RequestBody UserFavorites userFavorites) {
        StyleService.deleteStyle(userFavorites.getUserId(), userFavorites.getFavoriteContent());
        return R.ok("删除成功", userFavorites.getFavoriteContent());
    }

    /**
     * 更改风格名字
     */
    @PutMapping("/update")
    public R<String> updateStyleName(@RequestBody UserFavorites userFavorites) {
        StyleService.updateStyleName(userFavorites.getUserId(), userFavorites.getFavoriteContent());
        return R.ok("更改成功", userFavorites.getFavoriteContent());
    }
}
