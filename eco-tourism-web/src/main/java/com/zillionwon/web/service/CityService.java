package com.zillionwon.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zillionwon.common.core.util.StringUtils;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.PageQuery;
import com.zillionwon.web.domain.TableDataInfo;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务层处理
 *
 * @author Ciel3232
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询事件列表
     * @param bo 城市业务层对象
     * @param pageQuery 分页参数
     * @return 城市列表（分页）
     */
    public TableDataInfo<CityVO> queryPageList(City bo, PageQuery pageQuery) {
        LambdaQueryWrapper<City> lqw = buildQueryWrapper(bo);
        Page<CityVO> result = cityMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    // 通过城市名搜索城市
    public List<CityVO> getCitiesByCityName(String cityName) {
        return cityMapper.findByCityName(cityName);
    }

    public List<CityVO> getCitiesByTag(Long tagId) {
        return cityMapper.findCitiesByTag(tagId);
    }

    public List<CityVO> getRandomCities(int n) {
        return cityMapper.findRandomCitiesByTag(n);
    }

    // 获取随机城市
    public CityVO getRandomCity() {
        return cityMapper.findRandomCity();
    }

    /**
     * 查询城市列表（不分页）
     * @param bo 城市业务层对象
     * @return 城市列表
     */
    public List<CityVO> queryList(City bo) {
        LambdaQueryWrapper<City> lqw = buildQueryWrapper(bo);
        return cityMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<City> buildQueryWrapper(City bo) {
        LambdaQueryWrapper<City> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCityId() != null, City::getCityId, bo.getCityId());
        lqw.like(StringUtils.isNotBlank(bo.getCityName()), City::getCityName, bo.getCityName());
        return lqw;
    }

}
