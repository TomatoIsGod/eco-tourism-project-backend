package com.zillionwon.web.service;

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
}
