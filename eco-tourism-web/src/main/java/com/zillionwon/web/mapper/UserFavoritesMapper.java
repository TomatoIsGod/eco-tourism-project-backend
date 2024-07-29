package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.UserFavorites;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserFavoritesMapper {

    @Select("SELECT * FROM user_favorites WHERE user_id = #{userId}")
    List<String> findByUserId(@Param("userId") Long userId);

    @Select("SELECT * FROM user_favorites WHERE favorite_content LIKE #{favoriteContent} AND user_id = #{userId}")
    List<String> findByFavoriteContent(@Param("favoriteContent") String favoriteContent,@Param("userId") Long userId);

    @Insert("INSERT INTO user_favorites(user_id, favorite_content) VALUES(#{userId}, #{favoriteContent})")
    void insert(@Param("favoriteContent") String favoriteContent,@Param("userId") Long userId);

    @Delete("DELETE FROM user_favorites WHERE user_id = #{userId} AND favorite_content = #{favoriteContent}")
    void delete(@Param("favoriteContent") String favoriteContent,@Param("userId") Long userId);
}