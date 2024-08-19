package com.zillionwon.web.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.CityTag;
import com.zillionwon.web.domain.Tag;
import com.zillionwon.web.mapper.CityTagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 城市-标签关联
 *
 * @author InwardFlow
 */
@Service
@AllArgsConstructor
public class CityTagService {

    private final CityTagMapper cityTagMapper;

    /**
     * 通过城市ID获取标签列表
     * @param cityId 城市ID
     * @return 关联该城市的标签列表
     */
    public List<Tag> getTagsByCityId(Long cityId) {
        return cityTagMapper.selectTagsByCityId(cityId);
    }

    /**
     * 通过标签ID查询关联的城市列表
     * @param tagId 标签ID
     * @return 使用该标签的城市列表
     */
    public List<City> getCitiesByTagId(Long tagId) {
        return cityTagMapper.selectCitiesByTagId(tagId);
    }

    /**
     * 通过 cityId 或 tagId 查询关联
     *
     * @param cityTag 城市与标签关联
     * @return 使用该标签的城市列表
     */
    public List<CityTag> getCityTags(@RequestBody CityTag cityTag) {
        LambdaQueryWrapper<CityTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(ObjectUtil.isNotEmpty(cityTag.getTagId()), CityTag::getTagId, cityTag.getTagId());
        lqw.eq(ObjectUtil.isNotEmpty(cityTag.getCityId()), CityTag::getCityId, cityTag.getCityId());
        return cityTagMapper.selectList(lqw);
    }
}
