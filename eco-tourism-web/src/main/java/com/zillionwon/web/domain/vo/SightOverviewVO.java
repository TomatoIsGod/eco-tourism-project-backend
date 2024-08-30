package com.zillionwon.web.domain.vo;

import com.zillionwon.web.domain.SightOverview;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 景点总览
 *
 * @author CH
 */

@AutoMapper(target = SightOverview.class)
@EqualsAndHashCode(callSuper = false)
public class SightOverviewVO {
    private Long id;
    private String city;
    private Long sightId;
    private String name;
    private String level;
    private String rankTag;
    private String tagsId;
    private BigDecimal heat;
    private double score;
    private String raterCount;
    private String address;
    private String distanceFromCity;
    private double price;
    private String coverImgUrl;
    private String openTime;
    private String closeTime;

    // 目前数据结构里没有，先加来用
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getSightId() {
        return sightId;
    }

    public void setSightId(Long sightId) {
        this.sightId = sightId;
    }

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

    public String getTagsId() {
        return tagsId;
    }

    public void setTagsId(String tagsId) {
        this.tagsId = tagsId;
    }

    public BigDecimal getHeat() {
        return heat;
    }

    public void setHeat(BigDecimal heat) {
        this.heat = heat;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
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

    public String getDistanceFromCity() {
        return distanceFromCity;
    }

    public void setDistanceFromCity(String distanceFromCity) {
        this.distanceFromCity = distanceFromCity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
