package com.zillionwon.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序 API 配置类
 *
 * @author InwardFlow
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {

    private String appId;
    private String appSecret;

    // getter 和 setter 方法
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
