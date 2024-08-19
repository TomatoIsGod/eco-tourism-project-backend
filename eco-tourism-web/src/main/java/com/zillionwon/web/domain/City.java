package com.zillionwon.web.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 城市实体类
 *
 * @author InwardFlow
 */
@TableName("city")
public class City extends BaseEntity {

    /**
     * 城市ID
     */
    @TableId
    private Long cityId;

    /**
     * 城市名称
     */
    private String cityName;

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
}
