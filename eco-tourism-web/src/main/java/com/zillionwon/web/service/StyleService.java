package com.zillionwon.web.service;

import com.zillionwon.web.mapper.StyleMapper;
import com.zillionwon.web.mapper.UserFavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleService {
    @Autowired
    private StyleMapper StyleMapper;
    /**
     * 获取用户的收藏列表
     * @param StyleId 风格ID
     * @return 返回对应风格
     */
    public List<String> getStyleId(Long StyleId) {
        return StyleMapper.findByStyleId(StyleId);
    }

    /**
     * 获取用户的收藏列表
     * @param StyleName 风格名称
     * @return 返回对应风格
     */
public List<String> searchStyleName(String StyleName) {
        return StyleMapper.findByStyleName(StyleName);
    }

    /**
     * 添加新的风格
     * @param StyleId 风格ID
     * @param StyleName 风格名称
     */
    public void addStyle(Long StyleId, String StyleName) {
        StyleMapper.insert(StyleId, StyleName);
    }

    /**
     * 删除风格
     * @param StyleId 风格ID
     * @param StyleName 风格名称
     */
    public void deleteStyle(Long StyleId, String StyleName) {
        StyleMapper.delete(StyleId, StyleName);
    }

    /**
     * 更改风格名字
     */
    public void updateStyleName(Long StyleId, String StyleName) {
        StyleMapper.updateStyleName(StyleId, StyleName);
    }

}
