package com.zillionwon.web.service;

import com.zillionwon.web.domain.Style;
import com.zillionwon.web.mapper.StyleMapper;
import com.zillionwon.web.mapper.UserFavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StyleService {
    @Autowired
    private StyleMapper StyleMapper;
    /**
     * 获取用户的收藏列表
     * @param styleId 风格ID
     * @return 返回对应风格
     */
    public Style getStyleId(Long styleId) {
        return  StyleMapper.findByStyleId(styleId);
    }

    /**
     * 获取用户的收藏列表
     * @param styleName 风格名称
     * @return 返回对应风格
     */
public Style searchStyleName(String styleName) {
        return StyleMapper.findByStyleName(styleName);
    }

    /**
     * 添加新的风格
     * @param styleId 风格ID
     * @param styleName 风格名称
     */
    public void addStyle(Long styleId, String styleName) {
        StyleMapper.insert(styleId, styleName);
    }

    /**
     * 删除风格
     * @param styleId 风格ID
     * @param styleName 风格名称
     */
    public void deleteStyle(@RequestParam(required = false) Long styleId, @RequestParam(required = false)String styleName) {
        StyleMapper.delete(styleId, styleName);
    }

    /**
     * 更改风格名字
     */
    public void updateStyleName(Long styleId, String styleName) {
        StyleMapper.updateStyleName(styleId, styleName);
    }

}
