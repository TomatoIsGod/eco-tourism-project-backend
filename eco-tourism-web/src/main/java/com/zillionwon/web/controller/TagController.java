package com.zillionwon.web.controller;

import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.domain.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zillionwon.web.controller.CityController.getMockCities;

/**
 * 分类控制器
 * @author InwardFlow
 */
@Slf4j
@RestController
@RequestMapping("/webapi/mp/category")
@CrossOrigin
@Api(tags = "城市分类接口服务")
public class TagController {
    @GetMapping("/all")
    public R<Map<String, List<CityVO>>> getAll() {
        return R.ok(getMockCategory());
    }
    /**
     * 返回一个分类列表（待废弃）
     * @return 城市列表
     */
    private Map<String, List<CityVO>> getMockCategory() {
        // 首先获取模拟的城市列表
        Map<String, List<CityVO>> map = new HashMap<>();
        List<CityVO> cityList = getMockCities();
        for (CityVO city : cityList) {

            for (String tag : city.getTags()) {
                if (!map.containsKey(tag)) {
                    map.put(tag, new ArrayList<>());
                }
                map.get(tag).add(city);
            }
        }
        return map;
    }
}
