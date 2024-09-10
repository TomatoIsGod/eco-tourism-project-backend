package com.zillionwon.web.domain.dto;
import java.util.List;

/**
 * 快捷路书界面数据
 *
 * @author Ciel3232
 */
public class CityDetailDTO {
    private TransportationInfo transportationInfo;
    private WeatherInfo weatherInfo;
    private DiningInfo diningInfo;
    private List<SightInfo> sightInfo;
    //以上四个参数目前数据库里还没有

    // Getters and Setters

    public TransportationInfo getTransportationInfo() {
        return transportationInfo;
    }

    public void setTransportationInfo(TransportationInfo transportationInfo) {
        this.transportationInfo = transportationInfo;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public DiningInfo getDiningInfo() {
        return diningInfo;
    }

    public void setDiningInfo(DiningInfo diningInfo) {
        this.diningInfo = diningInfo;
    }

    public List<SightInfo> getSightInfo() {
        return sightInfo;
    }

    public void setSightInfo(List<SightInfo> sightInfo) {
        this.sightInfo = sightInfo;
    }

    public static class TransportationInfo {
        private String details;

        // Getters and Setters
        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }

    public static class WeatherInfo {
        private String details;

        // Getters and Setters
        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }

    public static class DiningInfo {
        private String details;

        // Getters and Setters
        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }

    public static class SightInfo {
        private String name;
        private String rankTag;
        private String score;
        private String address;
        private String description;

        // Getters and Setters
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

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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
