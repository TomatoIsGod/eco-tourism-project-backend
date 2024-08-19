package com.zillionwon.web.mapper;

import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.vo.CityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 城市 Mapper 接口类
 *
 * @author Ciel3232
 */
public interface CityMapper extends BaseMapperPlus<City, CityVO> {

    List<CityVO> selectCitiesByCondition(@Param("cityId") Long cityId, @Param("cityName") String cityName,
                                       @Param("tagId") Long tagId, @Param("tagName") String tagName);

    @Select("SELECT * FROM city WHERE city_name LIKE CONCAT('%', #{cityName}, '%')")
    List<CityVO> findByCityName(@Param("cityName") String cityName);

    @Select("SELECT c.* FROM city c JOIN city_tag ct ON c.city_id = ct.city_id WHERE ct.tag_id = #{tagId}")
    List<CityVO> findCitiesByTag(@Param("tagId") Long tagId);

    @Select("SELECT * FROM city ORDER BY RAND() LIMIT #{n}")
    List<CityVO> findRandomCitiesByTag(@Param("n") int n);

    @Select("SELECT * FROM city ORDER BY RAND() LIMIT 1")
    CityVO findRandomCity();
}
