package com.zillionwon.web.controller;

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
     * 查询城市信息 (不分页)
     *
     * @param cityId 城市ID
     * @param cityName 城市名
     * @param tagId 标签ID
     * @param tagName 标签名
     * @return 城市列表
     */
    @GetMapping
    public R<List<CityVO>> queryListByCondition(
            @RequestParam(required = false) Long cityId,
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) Long tagId,
            @RequestParam(required = false) String tagName
    ) {
        List<CityVO> cities = cityService.getCitiesByCondition(cityId, cityName, tagId, tagName);
        return R.ok(cities);
    }

    /**
     * 查询城市信息 (分页)
     *
     * @param cityBo    城市业务层对象
     * @param pageQuery 分页
     * @return 城市列表 (分页对象)
     * @deprecated
     */

    @Deprecated
    @GetMapping("/list")
    public TableDataInfo<CityVO> list(City cityBo, PageQuery pageQuery) {
        var list = cityService.queryPageList(cityBo, pageQuery).getRows();
        return TableDataInfo.build(list.stream().peek(cityVO -> cityVO.setTags(cityTagService.getTagsByCityId(cityVO.getCityId())))
                .collect(Collectors.toList()));
    }

    /**
     * 获取一个随机城市
     *
     * @return 城市信息
     */
    @GetMapping("/random")
    public R<CityVO> getRandomCity() {
        CityVO city = cityService.getRandomCity();
        if (city == null) {
            return R.fail("获取随机城市失败");
        } else {
            city.setTags(cityTagService.getTagsByCityId(city.getCityId()));
            return R.ok(city);
        }
    }

    /**
     * 获取 City 与 Tag 之间的映射关系
     *
     * @param tagId 标签ID
     * @param cityId 城市ID
     * @return 城市列表
     */

    @GetMapping("/queryCityTag")
    public R<List<CityTag>> getCitiesByTag(@RequestParam(required = false) Long tagId,
                                           @RequestParam(required = false) Long cityId) {
        return R.ok(cityTagService.getCityTags(new CityTag(tagId, cityId)));
    }

}
