package com.zillionwon.web.domain.vo;

import java.util.List;

/**
 * 标签视图类
 *
 * @author InwardFlow
 */
public class TagVO {
    private Long tagId;
    private String tagName;
    private List<CityVO> cities;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<CityVO> getCities() {
        return cities;
    }

    public void setCities(List<CityVO> cities) {
        this.cities = cities;
    }

}
