package com.zillionwon.web.controller;

import com.zillionwon.web.domain.dto.CityDetailDTO;
import com.zillionwon.web.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快捷路书/旅行指南
 *
 * @author Ciel3232
 */

@RestController
@RequestMapping("/api")
public class GuideController {

    @Autowired
    private SightService sightService;

    @GetMapping("/getCityDetail")
    public CityDetailDTO getCityDetail(@RequestParam String cityName) {
        return sightService.getCityDetail(cityName);
    }

}
