package com.zillionwon.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.StrUtil;
import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.City;
import com.zillionwon.web.domain.CityTag;
import com.zillionwon.web.domain.PageQuery;
import com.zillionwon.web.domain.TableDataInfo;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.service.CityService;
import com.zillionwon.web.service.CityTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 城市接口
 *
 * @author InwardFlow
 */

@SaIgnore
@Slf4j
@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityTagService cityTagService;

    /**
     * 获取城市列表信息
     *
     * @param city 城市
     * @return 城市列表
     */
    @GetMapping
    public R<List<CityVO>> queryList(City city) {
        List<CityVO> cities = cityService.queryList(city);
        if (cities.isEmpty()) {
            log.info("城市 {} 不存在", city);
            return R.fail("城市不存在");
        } else {
            return R.ok(cities.stream()
                        .peek(cityVO -> cityVO.setTags(cityTagService.getTagsByCityId(cityVO.getCityId())))
                        .collect(Collectors.toList()));
        }
    }

    /**
     * 查询城市信息 (分页)
     *
     * @param cityBo    城市业务层对象
     * @param pageQuery 分页
     * @return 城市列表 (分页对象)
     */
    @GetMapping("/list")
    public TableDataInfo<CityVO> list(City cityBo, PageQuery pageQuery) {
        return cityService.queryPageList(cityBo, pageQuery);
    }

    /**
     * 查询城市
     *
     * @param cityName 城市名
     * @param tagId    标签ID
     * @return 城市集合
     */
    @GetMapping("/search")
    public R<List<CityVO>> searchCities(@RequestParam(required = false) String cityName,
                                        @RequestParam(required = false) Long tagId) {
        List<CityVO> cities;
        if (!StrUtil.isBlank(cityName)) {
            cities = cityService.getCitiesByCityName(cityName);
        } else if (tagId != null) {
            cities = cityService.getCitiesByTag(tagId);
        } else {
            return R.fail("Invalid search parameters");
        }
        return cities.isEmpty() ? R.fail("No cities found") : R.ok(cities);
    }

    /**
     * 获取一个随机城市
     *
     * @return 城市信息
     */
    @GetMapping("/random")
    public R<CityVO> getRandomCity() {
        CityVO city = cityService.getRandomCity();
        return city != null ? R.ok(city) : R.fail("获取随机城市失败");
    }

    /**
     * 获取 City 与 Tag 之间的映射关系
     *
     * @param tagId 标签ID
     * @return 城市列表
     */
    @Deprecated
    @GetMapping("/queryCityTag")
    public R<List<CityTag>> getCitiesByTag(@RequestParam Long tagId, @RequestParam Long cityId) {
        return R.ok(cityTagService.getCityTags(new CityTag(tagId, cityId)));
    }

    /**
     * 查询城市信息 (不分页)
     *
     * @param cityBo 城市业务层对象
     * @return 城市列表
     */
    public List<CityVO> getAll(City cityBo) {
        return cityService.queryList(cityBo);
    }

}
