package com.zillionwon.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 城市-标签 关联
 *
 * @author InwardFlow
 */

@Data
@TableName("city_tag")
@AllArgsConstructor
public class CityTag {

    /**
     * 标签ID
     */
    @TableId(type = IdType.INPUT)
    private Long tagId;


    private Long cityId;

}
