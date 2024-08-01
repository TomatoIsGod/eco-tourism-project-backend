package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.Style;
import com.zillionwon.web.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 旅行风格接口
 *
 * @author black
 */

@Slf4j
@RestController
@RequestMapping("/style")
public class StyleController {
    private final StyleService styleService;

    @Autowired
    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    /**
     * 通过 ID 获取旅行风格
     * @param styleId 风格ID
     * @return 旅行风格
     */
    @GetMapping("/findById/{styleId}")
    public R<Style> findById(@PathVariable Long styleId) {
        try {
            return R.ok(Objects.requireNonNull(styleService.getStyleById(styleId)));
        } catch (NullPointerException e) {
            log.info("styleId 为 {} 的旅行风格不存在", styleId);
            return R.fail("未找到该旅行风格");
        }
    }

    /**
     * 通过名称获取旅行风格
     * @param styleName 旅行风格名称
     * @return 旅行风格
     */
    @GetMapping("/findByName/{styleName}")
    public R<Style> findByName(@PathVariable String styleName) {
        try {
            return R.ok(Objects.requireNonNull(styleService.searchStyleByName(styleName)));
        } catch (NullPointerException e) {
            log.info("styleName 为 {} 的旅行风格不存在", styleName);
            return R.fail("未找到该旅行风格");
        }
    }

    /**
     * 添加新的风格
     */
    @PostMapping
    public R<Style> addByName(@RequestParam String styleName) {
        List<Style> styles = styleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleName().equals(styleName)) {
                return R.fail("风格已存在");
            }
        }
        styleService.addStyle(styleName);
        Style newStyle = styleService.searchStyleByName(styleName);
        return R.ok("添加成功", newStyle);
    }

    /**
     * 删除风格
     */
    @DeleteMapping
    public R<Style> deleteByName(@RequestParam String styleName) {
        List<Style> styles = styleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleName().equals(styleName)) {
                long styleId = style.getStyleId();
                styleService.deleteStyle(styleId);
                return R.ok();
            }
        }
        return R.fail("风格本身不存在");
    }

    /**
     * 更改风格名字 (通过风格ID)
     * @param styleId 风格ID
     * @param styleName 风格名称
     */
    @PutMapping
    public R<Void> updateNameById(@RequestParam Long styleId, String styleName) {
        List<Style> styles = styleService.getAllStyle();
        for (Style style : styles) {
            if (style.getStyleId().equals(styleId)) {
                styleService.updateStyleName(styleId, styleName);
                return R.ok();
            }
        }
        return R.fail("风格本身不存在");
    }
}
