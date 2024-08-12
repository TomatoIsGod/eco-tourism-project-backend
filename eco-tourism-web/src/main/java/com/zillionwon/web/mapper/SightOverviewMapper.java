package com.zillionwon.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zillionwon.web.domain.SightOverview;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 景区总览 Mapper 接口类
 *
 * @author InwardFlow
 */

public interface SightOverviewMapper extends BaseMapper<SightOverview> {

    @Select("SELECT * FROM sight_overview WHERE city = #{city}")
    List<SightOverview> selectByCity(String city);
}
