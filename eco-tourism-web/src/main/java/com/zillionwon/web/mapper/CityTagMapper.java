package com.zillionwon.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.CityTag;
import com.zillionwon.web.domain.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 城市-标签映射关系 Mapper 类
 *
 * @author InwardFlow
 */
public interface CityTagMapper extends BaseMapper<CityTag> {

    /**
     * 根据标签ID查询关联的城市列表
     *
     * @param tagId 标签ID
     * @return 与标签ID关联的城市列表
     */
    @Select("SELECT * FROM city JOIN city_tag ON city.city_id = city_tag.city_id WHERE city_tag.tag_id = #{tagId}")
    List<City> selectCitiesByTagId(Long tagId);

    /**
     * 根据城市ID查询关联的标签列表
     *
     * @param cityId 城市ID
     * @return 与城市ID关联的标签列表
     */
    @Select("SELECT * FROM tag JOIN city_tag ON tag.tag_id = city_tag.tag_id WHERE city_tag.city_id = #{cityId}")
    List<Tag> selectTagsByCityId(Long cityId);

    @Select("SELECT * FROM city_tag WHERE city_id = #{cityId} AND tag_id = #{tagId}")
    List<CityTag> queryList(Long cityId, Long tagId);

}
