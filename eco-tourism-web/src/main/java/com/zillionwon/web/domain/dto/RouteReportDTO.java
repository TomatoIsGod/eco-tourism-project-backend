package com.zillionwon.web.domain.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 旅行详细路书主页数据
 */
public class RouteReportDTO {
    private String city;
    private int days;
    private List<DayDetail> dayDetails;

    // Getters and setters

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<DayDetail> getDayDetails() {
        return dayDetails;
    }

    public void setDayDetails(List<DayDetail> dayDetails) {
        this.dayDetails = dayDetails;
    }

    public static class DayDetail {
        private String suggestedPlayTime;
        private String routeOverview;
        private List<SightDetail> sights;

        // Getters and setters

        public String getSuggestedPlayTime() {
            return suggestedPlayTime;
        }

        public void setSuggestedPlayTime(String suggestedPlayTime) {
            this.suggestedPlayTime = suggestedPlayTime;
        }

        public String getRouteOverview() {
            return routeOverview;
        }

        public void setRouteOverview(String routeOverview) {
            this.routeOverview = routeOverview;
        }

        public List<SightDetail> getSights() {
            return sights;
        }

        public void setSights(List<SightDetail> sights) {
            this.sights = sights;
        }
    }

    public static class SightDetail {
        private String name;
        private String rankTag;
        private BigDecimal score;
        private String openTime;
        private String closeTime;
        private BigDecimal price;
        private String address;
        private String description;

        // Getters and setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRankTag() {
            return rankTag;
        }

        public void setRankTag(String rankTag) {
            this.rankTag = rankTag;
        }

        public BigDecimal getScore() {
            return score;
        }

        public void setScore(BigDecimal score) {
            this.score = score;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
