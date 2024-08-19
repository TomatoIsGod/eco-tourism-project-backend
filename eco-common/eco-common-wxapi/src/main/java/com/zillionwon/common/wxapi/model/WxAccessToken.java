package com.zillionwon.common.wxapi.model;

import jakarta.validation.constraints.NotNull;

/**
 * 微信小程序 getAccessToken 实体类
 *
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">获取接口调用凭据</a>
 * @author InwardFlow
 */

public class WxAccessToken {

    /**
     * 获取到的凭证
     */
    @NotNull
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    @NotNull
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
