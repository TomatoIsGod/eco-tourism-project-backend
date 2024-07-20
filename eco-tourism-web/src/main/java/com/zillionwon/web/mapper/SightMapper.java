package com.zillionwon.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zillionwon.web.domain.Sight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 旅行路书 Mapper接口类
 */
@Mapper
public interface SightMapper extends BaseMapper<Sight> {

    @Select("SELECT * FROM sight WHERE city = #{city}")
    List<Sight> selectByCity(String city);
}