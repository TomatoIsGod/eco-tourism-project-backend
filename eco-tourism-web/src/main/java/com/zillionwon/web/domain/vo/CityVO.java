package com.zillionwon.web.domain.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 城市视图类
 * @author InwardFlow
 */
public class CityVO {
    /**
     * 城市名
     */
    private String cityName;
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setTags(String... tags) {
        this.tags.addAll(Arrays.stream(tags).toList());
    }
    public void setTags(String tags) {
        this.tags.add(tags);
    }

    private List<String> tags = new ArrayList<>();
}
