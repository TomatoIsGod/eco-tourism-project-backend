package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.Tag;
import com.zillionwon.web.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * 标签 Mapper 接口类
 *
 * @author Ciel3232
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    @Select("SELECT t.* FROM tag t JOIN city_tag ct ON t.tag_id = ct.tag_id WHERE ct.city_id = #{cityId}")
    List<TagVO> findTagsByCity(@Param("cityId") Long cityId);
}
