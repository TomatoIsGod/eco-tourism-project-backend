package com.zillionwon.web.controller;

import com.zillionwon.web.domain.vo.EventBo;
import com.zillionwon.web.domain.PageQuery;
import com.zillionwon.web.domain.vo.EventVO;
import com.zillionwon.web.domain.R;
import com.zillionwon.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 旅行计划
 *
 * @author Ciel3232
 */

@RestController
public class PlanController {

    @Autowired
    private EventService eventService;

    @GetMapping("/itinerary/travelStyle")
    public R<List<EventVO>> getTravelStyleData(EventBo eventBo, PageQuery pageQuery) {
        return eventService.getTravelStyleData(eventBo, pageQuery);
    }

    @GetMapping("/itinerary/routeReport")
    public R<EventVO> getRouteReportData(EventBo eventBo) {
        return eventService.getRouteReportData(eventBo);
    }

    @GetMapping("/itinerary/routeReport/collect")
    public R<Void> collectRouteReport(EventBo eventBo) {
        return eventService.collectRouteReport(eventBo);
    }

    @GetMapping("/itinerary/attractionDetails")
    public R<EventVO> getAttractionDetails(EventBo eventBo) {
        return eventService.getAttractionDetails(eventBo);
    }

    @GetMapping("/itinerary/collectionList")
    public R<List<EventVO>> getCollectionList(EventBo eventBo, PageQuery pageQuery) {
        return eventService.getCollectionList(eventBo, pageQuery);
    }
}
