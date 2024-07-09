package com.zillionwon.web.controller;

import cn.hutool.core.util.StrUtil;
import com.zillionwon.web.domain.CityVO;
import com.zillionwon.web.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 获取城市列表
 *
 * @author InwardFlow
 */
@Slf4j
@RestController
@RequestMapping("/webapi/mp/city")
@CrossOrigin
@Api(tags = "城市接口服务")
public class CityController {

    private final List<CityVO> cities = getMockCities();
    private final List<String> tags = getMockTags();

     // 添加查询指定城市的 POST 接口
    @PostMapping("/search")
    public ResponseEntity<R<List<CityVO>>> searchCity(@RequestBody Map<String, String> searchParams) {
        String cityName = searchParams.get("cityName");
        if (StrUtil.isBlank(cityName)) {
            return ResponseEntity.badRequest().body(R.fail("城市名称不能为空"));
        }
        List<CityVO> filteredCities = cities.stream()
                                            .filter(c -> c.getCityName().equalsIgnoreCase(cityName))
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(R.ok("查询成功", filteredCities));
    } 

    /**
     * 通过输入城市名称，搜索相关城市
     *
     * @param cityName 城市名称
     * @return 城市列表
     */
    @ApiOperation("获取城市列表")
    @GetMapping
    public R<List<CityVO>> getCityList(@Parameter(name = "城市名") String cityName) {
        if (!StrUtil.isBlank(cityName)) {
            return R.ok(cities.stream().filter(o -> o.getCityName().equals(cityName)).collect(Collectors.toList()));
        }
        return R.ok(cities);
    }

    /**
     * 通过输入名称，搜索相关城市
     *
     * @return 城市
     */
    // @ApiOperation("随机城市")
    // @GetMapping("/random")
    // public R<CityVO> getRandomCity() {
    //     /*
    //       SELECT * FROM `table` AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM `table`)-(SELECT MIN(id) FROM `table`))+(SELECT MIN(id) FROM `table`)) AS id) AS t2
    //       WHERE t1.id >= t2.id
    //       ORDER BY t1.id LIMIT 1;
    //      */
    //     return R.ok(cities.get((int) (Math.random() + 0.5)));
    // }

    @PostMapping("/random")
    public ResponseEntity<R<CityVO>> getRandomCity() {
        if (cities.isEmpty()) {
            return ResponseEntity.badRequest().body(R.fail("没有城市可选"));
        }
        int randomIndex = (int) (Math.random() * cities.size());
        CityVO randomCity = cities.get(randomIndex);
        return ResponseEntity.ok(R.ok("随机城市获取成功", randomCity));
    }
    

    /**
     * 返回一个城市列表（待废弃）
     *
     * @return 城市列表
     */
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
