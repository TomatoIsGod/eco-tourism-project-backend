package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<TagVO> {

    @Select("SELECT t.* FROM tag t JOIN city_tag ct ON t.id = ct.tag_id WHERE ct.city_id = #{cityId}")
    List<TagVO> findTagsByCity(@Param("cityId") Long cityId);
}
