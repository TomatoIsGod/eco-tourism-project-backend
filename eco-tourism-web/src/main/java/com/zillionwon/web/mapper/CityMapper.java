package com.zillionwon.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zillionwon.web.domain.CityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper extends BaseMapper<CityVO> {

    @Select("SELECT * FROM cities WHERE city_name LIKE CONCAT('%', #{cityName}, '%')")
    List<CityVO> findByCityName(@Param("cityName") String cityName);

    @Select("SELECT c.* FROM city c JOIN city_tag ct ON c.id = ct.city_id WHERE ct.tag_id = #{tagId}")
    List<CityVO> findCitiesByTag(@Param("tagId") Long tagId);

    @Select("SELECT * FROM city ORDER BY RAND() LIMIT #{n}")
    List<CityVO> findRandomCitiesByTag(@Param("n") int n);

    @Select("SELECT * FROM city ORDER BY RAND() LIMIT 1")
    CityVO findRandomCity();
}
