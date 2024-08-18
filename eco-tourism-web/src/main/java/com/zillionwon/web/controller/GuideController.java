package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.dto.CityDetailDTO;
import com.zillionwon.web.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快捷路书（旅行指南）
 *
 * @author Ciel3232
 */

@RestController
@RequestMapping("/city/detail")
public class GuideController {

    @Autowired
    private SightService sightService;

    /**
     * 获取城市详细信息
     * @param cityName 城市名
     * @return 城市详细信息
     */
    @GetMapping
    public R<CityDetailDTO> getCityDetail(@RequestParam String cityName) {
        return R.ok(sightService.getCityDetail(cityName));
    }

}
