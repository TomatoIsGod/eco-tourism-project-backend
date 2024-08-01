package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.Style;
import com.zillionwon.web.domain.UserFavorites;
import com.zillionwon.web.mapper.StyleMapper;
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
    @GetMapping("/Style")
    public R<Style> getStyleId(@RequestParam Long styleId) {
        Style style = StyleService.getStyleById(styleId);
        if (style != null) {
            System.out.println(style);
            return R.ok("搜索成功", style);
        } else return R.ok("搜索失败");
    }

    /**
     * 搜索对应风格名称的风格
     */
    @GetMapping("/StyleSearch")
    public R<Style> searchStyleName(@RequestParam String styleName) {
        Style style = StyleService.searchStyleByName(styleName);
        if (style != null) {
            System.out.println(style);
            return R.ok("搜索成功", style);
        } else return R.ok("搜索失败");
    }

    /**
     * 添加新的风格
     */
    @PostMapping("/StyleAdd")
    public R<Style> addStyle(@RequestParam String styleName) {
        List<Style> styles = StyleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleName().equals(styleName)) {
                return R.ok("风格已存在");
            }
        }
        StyleService.addStyle(styleName);
        Style newStyle = StyleService.searchStyleByName(styleName);
        return R.ok("添加成功", newStyle);
    }

    /**
     * 删除风格
     */
    @DeleteMapping("/StyleDelete")
    public R<Style> deleteStyle(@RequestParam String styleName) {
        List<Style> styles = StyleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleName().equals(styleName)) {
                long styleId = style.getStyleId();
                StyleService.deleteStyle(styleId);
                return R.ok("删除成功");
            }
        }
        return R.ok("风格本身不存在");
    }

    /**
     * 更改风格名字
     */
    @PutMapping("/StyleUpdate")
    public R<String> updateStyleName(@RequestParam Long styleId, String styleName) {
        List<Style> styles = StyleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleId().equals(styleId)) {
                StyleService.updateStyleName(styleId, styleName);
                return R.ok("更改成功", styleName);
            }
        }
        return R.ok("风格本身不存在");
    }
}
