package com.zillionwon.web.domain.dto;
import java.math.BigDecimal;

/**
 * 旅行路书点击进任一景区详情界面数据
 */
public class SightDetailDTO {
    private String name;
    private String rankTag;
    private BigDecimal score;
    private String openTime;
    private String closeTime;
    private BigDecimal price;
    private String address;
    private String description;
    //ui里“单独景点展开”页面中增加的参数内容
    private String suggestedPlayTime;//推荐游玩时长
    private String recommendedReason;//推荐理由
    private String locationOverview;//地点概览
    private String localPlayFeatures;//当地游玩特色

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

    public String getSuggestedPlayTime() {
        return suggestedPlayTime;
    }

    public void setSuggestedPlayTime(String suggestedPlayTime) {
        this.suggestedPlayTime = suggestedPlayTime;
    }

    public String getRecommendedReason() {
        return recommendedReason;
    }

    public void setRecommendedReason(String recommendedReason) {
        this.recommendedReason = recommendedReason;
    }

    public String getLocationOverview() {
        return locationOverview;
    }

    public void setLocationOverview(String locationOverview) {
        this.locationOverview = locationOverview;
    }

    public String getLocalPlayFeatures() {
        return localPlayFeatures;
    }

    public void setLocalPlayFeatures(String localPlayFeatures) {
        this.localPlayFeatures = localPlayFeatures;
    }
}
