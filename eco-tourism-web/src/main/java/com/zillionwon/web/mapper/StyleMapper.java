package com.zillionwon.web.mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StyleMapper {
    @Select("SELECT * FROM style WHERE style_id = #{SyleId}")
    List<String> findByStyleId(@Param("StyleId") Long StyleId);

    @Select("SELECT * FROM style WHERE style_name LIKE #{StyleName}")
    List<String> findByStyleName(@Param("StyleName") String StyleName);

    @Insert("INSERT INTO style (style_id, style_name) VALUES(#{StyleId}, #{StyleName})")
    void insert(@Param("StyleId") Long StyleId,@Param("StyleName") String StyleName);

    @Delete("DELETE FROM style WHERE style_name = #{StyleName} OR sttyle_id = #{StyleId}")
    void delete(@Param("StyleId") Long StyleId,@Param("StyleName") String StyleName);

    @Update("UPDATE style SET style_name = #{StyleName} WHERE style_id = #{StyleId}")
    void updateStyleName(@Param("StyleId") Long StyleId,@Param("StyleName") String StyleName);
}

