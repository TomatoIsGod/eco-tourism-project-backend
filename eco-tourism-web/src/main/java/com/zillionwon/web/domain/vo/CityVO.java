package com.zillionwon.web.domain.vo;

import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.Tag;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 城市视图类
 * @author InwardFlow
 */
@Data
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

}
