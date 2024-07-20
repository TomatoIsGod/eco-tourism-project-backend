package com.zillionwon.web.service;
import com.zillionwon.web.domain.dto.CityDetailDTO;
import com.zillionwon.web.domain.dto.RouteReportDTO;
import com.zillionwon.web.domain.dto.SightDetailDTO;
import com.zillionwon.web.domain.Sight;
import com.zillionwon.web.mapper.SightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SightService {

    @Autowired
    private SightMapper sightMapper;

    /**
     * 旅行路书详情service
     * @param city
     * @return
     */
    public RouteReportDTO getRouteReportData(String city) {
        List<Sight> sights = sightMapper.selectByCity(city);

        RouteReportDTO routeReport = new RouteReportDTO();
        routeReport.setCity(city);
        routeReport.setDays(2);

        List<RouteReportDTO.DayDetail> dayDetails = new ArrayList<>();

        for (int day = 1; day <= 2; day++) {
            RouteReportDTO.DayDetail dayDetail = new RouteReportDTO.DayDetail();
            dayDetail.setSuggestedPlayTime("8小时"); // 示例数据
            dayDetail.setRouteOverview(null); // 先设置为null

            List<RouteReportDTO.SightDetail> sightDetails = new ArrayList<>();
            for (Sight sight : sights) {
                RouteReportDTO.SightDetail sightDetail = new RouteReportDTO.SightDetail();
                sightDetail.setName(sight.getName());
                sightDetail.setRankTag(sight.getRankTag());
                sightDetail.setScore(sight.getScore());
                sightDetail.setOpenTime(sight.getOpenTime());
                sightDetail.setCloseTime(sight.getCloseTime());
                sightDetail.setPrice(sight.getPrice());
                sightDetail.setAddress(sight.getAddress());
                sightDetail.setDescription(sight.getDescription());

                sightDetails.add(sightDetail);
            }

            dayDetail.setSights(sightDetails);
            dayDetails.add(dayDetail);
        }

        routeReport.setDayDetails(dayDetails);

        return routeReport;
    }

    /**
     * 旅行路书详情service
     * 具体景区详情数据
     * @param city
     * @param day
     * @return
     */
    public RouteReportDTO.DayDetail getDayDetail(String city, int day) {
        List<Sight> sights = sightMapper.selectByCity(city);

        RouteReportDTO.DayDetail dayDetail = new RouteReportDTO.DayDetail();
        dayDetail.setSuggestedPlayTime("8小时"); // 示例数据
        dayDetail.setRouteOverview(null); // 先设置为null

        List<RouteReportDTO.SightDetail> sightDetails = new ArrayList<>();
        for (Sight sight : sights) {
            RouteReportDTO.SightDetail sightDetail = new RouteReportDTO.SightDetail();
            sightDetail.setName(sight.getName());
            sightDetail.setRankTag(sight.getRankTag());
            sightDetail.setScore(sight.getScore());
            sightDetail.setOpenTime(sight.getOpenTime());
            sightDetail.setCloseTime(sight.getCloseTime());
            sightDetail.setPrice(sight.getPrice());
            sightDetail.setAddress(sight.getAddress());
            sightDetail.setDescription(sight.getDescription());

            sightDetails.add(sightDetail);
        }

        dayDetail.setSights(sightDetails);
        return dayDetail;
    }

    /**
     * 快捷路书城市详情数据
     * @param sightId
     * @return
     */
    public SightDetailDTO getSightDetail(Long sightId) {
        Sight sight = sightMapper.selectById(sightId);

        SightDetailDTO sightDetail = new SightDetailDTO();
        sightDetail.setName(sight.getName());
        sightDetail.setRankTag(sight.getRankTag());
        sightDetail.setScore(sight.getScore());
        sightDetail.setOpenTime(sight.getOpenTime());
        sightDetail.setCloseTime(sight.getCloseTime());
        sightDetail.setPrice(sight.getPrice());
        sightDetail.setAddress(sight.getAddress());
        sightDetail.setDescription(sight.getDescription());
        sightDetail.setSuggestedPlayTime(null); // 暂时设置为null
        sightDetail.setRecommendedReason(null); // 暂时设置为null
        sightDetail.setLocationOverview(null); // 暂时设置为null
        sightDetail.setLocalPlayFeatures(null); // 暂时设置为null

        return sightDetail;
    }

     public CityDetailDTO getCityDetail(String cityName) {
        CityDetailDTO cityDetail = new CityDetailDTO();

        // 获取交通信息
        CityDetailDTO.TransportationInfo transportationInfo = new CityDetailDTO.TransportationInfo();
        transportationInfo.setDetails("交通信息示例");
        cityDetail.setTransportationInfo(transportationInfo);

        // 获取天气信息
        CityDetailDTO.WeatherInfo weatherInfo = new CityDetailDTO.WeatherInfo();
        weatherInfo.setDetails("天气信息示例");
        cityDetail.setWeatherInfo(weatherInfo);

        // 获取饮食信息
        CityDetailDTO.DiningInfo diningInfo = new CityDetailDTO.DiningInfo();
        diningInfo.setDetails("饮食信息示例");
        cityDetail.setDiningInfo(diningInfo);

        // 获取景区信息
        List<Sight> sights = sightMapper.selectByCity(cityName);
        List<CityDetailDTO.SightInfo> sightInfos = sights.stream().map(sight -> {
            CityDetailDTO.SightInfo sightInfo = new CityDetailDTO.SightInfo();
            sightInfo.setName(sight.getName());
            sightInfo.setRankTag(sight.getRankTag());
            sightInfo.setScore(sight.getScore().toString());
            sightInfo.setAddress(sight.getAddress());
            sightInfo.setDescription(sight.getDescription());
            return sightInfo;
        }).collect(Collectors.toList());
        cityDetail.setSightInfo(sightInfos);

        return cityDetail;
    }
}