package com.zillionwon.web.domain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 景点总览实体类
 * @author InwardFlow
 */
public class SightOverview extends BaseEntity {
    /**
     * 景点 ID
     */
    private Long sightId;
    /**
     * 名称
     */
    private String name;
    /**
     * A 级
     */
    private String level;
    /**
     * 特殊标签（例如榜单排名）
     */
    private String rankTag;
    /**
     * 标签
     */
    private List<String> tags = new ArrayList<>();
    /**
     * 热度
     */
    private Double heat;
    /**
     * 评分
     */
    private Double score;
    /**
     * 评分人数
     */
    // TODO： 后续要进行字符串处理
    private String raterCount;
    /**
     * 地址
     */
    private String address;
    /**
     * 关闭时间
     */
    private String closeTime;

    public String getDistanceFromCity() {
        return distanceFromCity;
    }

    public void setDistanceFromCity(String distanceFromCity) {
        this.distanceFromCity = distanceFromCity;
    }

    /**
     * 距市中心距离（千米）
     */
    private String distanceFromCity;
    /**
     * 门票价格（取最低金额）
     */
    private Double price;
    /**
     * 缩略图地址 220x140
     */
    private URL coverImgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRankTag() {
        return rankTag;
    }

    public void setRankTag(String rankTag) {
        this.rankTag = rankTag;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getHeat() {
        return heat;
    }

    public void setHeat(Double heat) {
        this.heat = heat;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRaterCount() {
        return raterCount;
    }

    public void setRaterCount(String raterCount) {
        this.raterCount = raterCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public URL getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(URL coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public SightOverview() {
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    // Builder类
    public static class Builder {
        private String name;
        private String level;
        private String rankTag;
        private List<String> tags = new ArrayList<>();
        private Double heat;
        private Double score;
        private String raterCount;
        private String address;
        private String distanceFromCity;
        private Double price;
        private URL smallImageUrl;
        private String closeTime;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder rankTag(String rankTag) {
            this.rankTag = rankTag;
            return this;
        }

        public Builder tag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder heat(Double heat) {
            this.heat = heat;
            return this;
        }

        public Builder score(Double score) {
            this.score = score;
            return this;
        }

        public Builder raterCount(String raterCount) {
            this.raterCount = raterCount;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder distanceFromCity(String distanceFromCity) {
            this.distanceFromCity = distanceFromCity;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder smallImageUrl(URL smallImageUrl) {
            this.smallImageUrl = smallImageUrl;
            return this;
        }

        public SightOverview build() {
            return new SightOverview(this);
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }
        public Builder closeTime(String closeTime) {
            this.closeTime = closeTime;
            return this;
        }
    }
    private SightOverview(Builder builder) {
        this.name = builder.name;
        this.level = builder.level;
        this.rankTag = builder.rankTag;
        this.tags = builder.tags;
        this.heat = builder.heat;
        this.score = builder.score;
        this.raterCount = builder.raterCount;
        this.address = builder.address;
        this.distanceFromCity = builder.distanceFromCity;
        this.price = builder.price;
        this.coverImgUrl = builder.smallImageUrl;
        this.closeTime = builder.closeTime;
    }
}
