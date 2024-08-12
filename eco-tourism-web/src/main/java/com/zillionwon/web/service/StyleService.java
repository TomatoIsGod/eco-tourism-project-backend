package com.zillionwon.web.service;

import com.zillionwon.web.domain.Style;
import com.zillionwon.web.mapper.StyleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StyleService {
    @Autowired
    private StyleMapper StyleMapper;

    /**
     * @return 返回所有风格
     */
    public List<Style> getAllStyle() {
        return StyleMapper.findAll();
    }

    /**
     * 获取用户的收藏列表
     *
     * @param styleId 风格ID
     * @return 返回对应风格
     */
    public Style getStyleById(Long styleId) {
        return StyleMapper.findByStyleId(styleId);
    }

    /**
     * 获取用户的收藏列表
     *
     * @param styleName 风格名称
     * @return 返回对应风格
     */
    public Style searchStyleByName(String styleName) {
        return StyleMapper.findByStyleName(styleName);
    }

    /**
     * 添加新的风格
     *
     * @param styleName 风格名称
     */
    public void addStyle(String styleName) {
        StyleMapper.insert(styleName);
    }

    /**
     * 删除风格
     *
     * @param styleId 风格ID
     */
    public void deleteStyle(@RequestParam Long styleId) {
        StyleMapper.delete(styleId);
    }

    /**
     * 更改风格名字
     */

    public void updateStyleName(Long styleId, String styleName) {
        StyleMapper.updateStyleName(styleId, styleName);
    }

}
