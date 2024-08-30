package com.zillionwon.web.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用户收藏类型
 *
 * @author CH
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FavoriteType {

    /**
     * 城市
     */
    City(1, "收藏城市"),

    /**
     * 景点
     */
    Sight(2, "收藏景点"),

    /**
     * 旅行计划
     */
    Report(3, "收藏旅游规划报告");

    /**
     * 枚举编码
     */
    private Integer code;

    /**
     * 枚举描述
     */
    private String desc;
}
