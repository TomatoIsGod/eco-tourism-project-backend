package com.zillionwon.web.domain.vo;

import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.Tag;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * 城市视图类
 * @author InwardFlow
 */

@AutoMapper(target = City.class)
@EqualsAndHashCode(callSuper = false)
public class CityVO {

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 标签
     */
    private List<Tag> tags;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void setTags(Tag... tags) {
        this.tags.addAll(Arrays.stream(tags).toList());
    }
    public void setTags(Tag tags) {
        this.tags.add(tags);
    }

}
