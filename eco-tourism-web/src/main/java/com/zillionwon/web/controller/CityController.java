package com.zillionwon.web.controller;

import cn.hutool.core.util.StrUtil;
import com.zillionwon.web.domain.R;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 城市接口
 *
 * @author InwardFlow
 */
@Slf4j
@RestController
@RequestMapping("/webapi/mp/city")
@CrossOrigin
public class CityController {

    private final List<CityVO> cities = getMockCities();
    private final List<String> tags = getMockTags();

    @Autowired
    private CityService cityService;

    /**
     * 查询城市
     * @param cityName 城市名
     * @param tagId 标签ID
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
     * @return 城市信息
     */
    @GetMapping("/random")
    public R<CityVO> getRandomCity() {
        CityVO city = cityService.getRandomCity();
        return city != null ? R.ok(city) : R.fail("No cities available");
    }

    /**
     * 获取城市列表
     * @param cityName 城市名
     * @return 城市列表
     */
    @GetMapping
    public R<List<CityVO>> getCityList(@RequestParam(required = false) String cityName) {
        if (!StrUtil.isBlank(cityName)) {
            return R.ok(cities.stream().filter(o -> o.getCityName().equals(cityName)).collect(Collectors.toList()));
        }
        return R.ok(cities);
    }

    @PostMapping("/list")
    public ResponseEntity<R<List<CityVO>>> getCityList(@RequestBody(required = false) Map<String, String> filters) {
        Stream<CityVO> cityStream = cities.stream();
        if (filters != null && !filters.isEmpty()) {
            String tagFilter = filters.get("tag");
            if (StrUtil.isNotBlank(tagFilter)) {
                cityStream = cityStream.filter(c -> c.getTags().contains(tagFilter));
            }
        }
        List<CityVO> filteredCities = cityStream.collect(Collectors.toList());
        return ResponseEntity.ok(R.ok("城市列表获取成功", filteredCities));
    }

    // 添加新的端点: 根据标签ID获取城市列表
    @GetMapping("/citiesByTag")
    public ResponseEntity<List<CityVO>> getCitiesByTag(@RequestParam Long tagId) {
        List<CityVO> cities = cityService.getCitiesByTag(tagId);
        return ResponseEntity.ok(cities);
    }

    protected static List<CityVO> getMockCities() {
        List<CityVO> lstCities = new ArrayList<>();
        CityVO city = new CityVO();
        city.setCityName("苏州");
        city.setTags("江南水乡");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("南京");
        city.setTags("江南水乡", "古都名城");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("杭州");
        city.setTags("江南水乡", "古都名城");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("乌镇");
        city.setTags("江南水乡");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("西安");
        city.setTags("古都名城");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("北京");
        city.setTags("古都名城");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("桂林");
        city.setTags("自然风光");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("三亚");
        city.setTags("自然风光");
        lstCities.add(city);

        city = new CityVO();
        city.setCityName("昆明");
        city.setTags("自然风光");
        lstCities.add(city);
        return lstCities;
    }

    private static List<String> getMockTags() {
        return List.of(new String[]{"江南水乡", "古都名城"});
    }

    /**
     * 通过输入城市ID，搜索相关城市
     *
     * @param cityId 城市ID
     * @return 城市信息
     */
    @ApiOperation("通过ID获取城市")
    @GetMapping("/{cityId}")
    public R<CityVO> getCityById(@Parameter(name = "城市ID") @PathVariable Long cityId) {
        return R.ok(Objects.requireNonNull(cities.get(Math.toIntExact(cityId))));
    }
}
