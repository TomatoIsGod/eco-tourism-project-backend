package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.SightOverview;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户收藏接口
 *
 * @author CH
 */

@Mapper
public interface UserFavoritesMapper {

    @Select("SELECT s.* FROM sight_overview s inner join user_favorite f on f.object_id = s.sight_id WHERE f.type = 2 and f.user_id = #{userId}")
    List<SightOverview> findSightsByUserId(Long userId);

    @Select("SELECT s.* FROM sight_overview s inner join user_favorite f on f.object_id = s.sight_id WHERE f.type=2 and f.user_id = #{userId} and f.object_id = #{objId}")
    SightOverview findSightByObjectId(Long userId, Long objId);

    @Insert("INSERT INTO user_favorite(user_id, object_id, type) VALUES(#{userId}, #{objId}, #{type})")
    void insert(@Param("userId") Long userId, @Param("type") int type, @Param("objId") Long objId);

    @Delete("DELETE FROM user_favorite WHERE type=#{type} and user_id = #{userId} AND object_id = #{objId}")
    void delete(@Param("userId") Long userId, @Param("type") int type, @Param("objId") Long objId);

    @Select("SELECT s.* FROM city s inner join user_favorite f on f.object_id=s.city_id WHERE f.type=#{type} and f.user_id = #{userId}")
    List<City> findCitiesByUserId(Long userId, int type);

    @Select("SELECT s.* FROM city s inner join user_favorite f on f.object_id=s.city_id WHERE f.type=#{type} and f.user_id = #{userId} and f.object_id = #{objId}")
    City findCityByObjectId(Long userId, Long objId, int type);
}
