package com.zillionwon.web.mapper;
import com.zillionwon.web.domain.Style;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StyleMapper {
    @Select("SELECT * FROM tour_style WHERE 1=1")
    List<Style> findAll();

    @Select("SELECT * FROM tour_style WHERE style_id = #{styleId}")
    Style findByStyleId(@Param("styleId") Long styleId);

    @Select("SELECT * FROM tour_style WHERE style_name LIKE CONCAT ('%',#{styleName},'%')")
    Style findByStyleName(@Param("styleName") String styleName);

    @Insert("INSERT INTO tour_style (style_name) VALUES(#{styleName})")
    void insert(@Param("styleName") String styleName);

    @Delete("DELETE FROM tour_style where style_id = #{styleId}")
    void delete(@Param("styleId") Long styleId);

    @Update("UPDATE tour_style SET style_name = #{styleName} WHERE style_id = #{styleId}")
    void updateStyleName(@Param("styleId") Long StyleId,@Param("styleName") String styleName);
}

