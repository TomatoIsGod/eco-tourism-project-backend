package com.zillionwon.common.wxapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序 API 配置类
 *
 * @author InwardFlow
 */

@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    /**
     * 微信小程序 AppId
     */
    private String appId;

    /**
     * 微信小程序密钥
     */
    private String appSecret;

    /**
     * 微信小程序 access_token
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
