package com.zillionwon.web.controller;

import com.zillionwon.web.domain.R;
import com.zillionwon.web.domain.vo.EventBo;
import com.zillionwon.web.domain.vo.EventVO;
import com.zillionwon.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 旅行指南
 *
 * @author Ciel3232
 */
@RestController
public class GuideController {

    @Autowired
    private EventService eventService;

    @GetMapping("/itinerary/cityDetails")
    public R<EventVO> getCityDetails(EventBo eventBo) {
        return eventService.getCityDetails(eventBo);
    }

    @GetMapping("/itinerary/weather")
    public R<EventVO> getWeather(EventBo eventBo) {
        return eventService.getWeather(eventBo);
    }

    @GetMapping("/itinerary/transportation")
    public R<EventVO> getTransportation(EventBo eventBo) {
        return eventService.getTransportation(eventBo);
    }

    @GetMapping("/itinerary/attractions")
    public R<List<EventVO>> getAttractions(EventBo eventBo) {
        return eventService.getAttractions(eventBo);
    }

    @GetMapping("/itinerary/food")
    public R<List<EventVO>> getFood(EventBo eventBo) {
        return eventService.getFood(eventBo);
    }
}
