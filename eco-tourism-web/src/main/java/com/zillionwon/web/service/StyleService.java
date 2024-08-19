package com.zillionwon.web.service;

import com.zillionwon.web.domain.Style;
import com.zillionwon.web.mapper.StyleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 旅游风格
 *
 * @author black
 */

@Service
public class StyleService {
    @Autowired
    private StyleMapper styleMapper;

    /**
     * @return 返回所有风格
     */
    public List<Style> getAllStyle() {
        return styleMapper.findAll();
    }

    /**
     * 获取用户的收藏列表
     *
     * @param styleId 风格ID
     * @return 返回对应风格
     */
    public Style getStyleById(Long styleId) {
        return styleMapper.findByStyleId(styleId);
    }

    /**
     * 获取用户的收藏列表
     *
     * @param styleName 风格名称
     * @return 返回对应风格
     */
    public Style searchStyleByName(String styleName) {
        return styleMapper.findByStyleName(styleName);
    }

    /**
     * 添加新的风格
     *
     * @param styleName 风格名称
     */
    public int addStyle(String styleName) {
        return styleMapper.insert(styleName);
    }

    /**
     * 删除风格
     *
     * @param styleId 风格ID
     */
    public void deleteStyle(@RequestParam Long styleId) {
        styleMapper.delete(styleId);
    }

    /**
     * 更改风格名字
     */

    public void updateStyleName(Long styleId, String styleName) {
        styleMapper.updateStyleName(styleId, styleName);
    }

}
