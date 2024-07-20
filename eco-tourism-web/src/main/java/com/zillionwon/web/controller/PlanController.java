package com.zillionwon.web.controller;

import com.zillionwon.web.domain.dto.RouteReportDTO;
import com.zillionwon.web.domain.dto.SightDetailDTO;
import com.zillionwon.web.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 旅行详细路书接口
 */
@RestController
@RequestMapping("/api")
public class PlanController {

    @Autowired
    private SightService sightService;

    /**
     * 城市详情数据
     * @param city
     * @return
     */
    @GetMapping("/getRouteReportData")
    public RouteReportDTO getRouteReportData(@RequestParam String city) {
        return sightService.getRouteReportData(city);
    }

    /**
     * 城市详情数据：点击“第1天/第2天...”
     * @param city
     * @param day
     * @return
     */
    @GetMapping("/getDayDetail")
    public RouteReportDTO.DayDetail getDayDetail(@RequestParam String city, @RequestParam int day) {
        return sightService.getDayDetail(city, day);
    }
    /**
     * 景区详情数据
     * @param sightId
     * @return
     */
    @GetMapping("/getSightDetail")
    public SightDetailDTO getSightDetail(@RequestParam Long sightId) {
        return sightService.getSightDetail(sightId);
    }
}
