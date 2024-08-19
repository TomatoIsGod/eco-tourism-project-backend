package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.Sight;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserFavoritesSightMapper {

    @Select("SELECT * FROM user_favorites_sight WHERE user_id = #{userId}")
    List<Sight> findByUserId(@Param("userId") Long userId);

    @Insert("INSERT INTO user_favorites_sight(user_id, favorite_sight) VALUES(#{userId}, #{favoriteSight})")
    void insert(@Param("favoriteSight") String favoriteSight,@Param("userId") Long userId);

    @Delete("DELETE FROM user_favorites_sight WHERE user_id = #{userId} AND favorite_sight = #{favoriteSight}")
    void delete(@Param("favoriteSight") String favoriteSight,@Param("userId") Long userId);
}