package com.zillionwon.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信小程序 API 配置类
 *
 * @author InwardFlow
 */

@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

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

    @Bean(name = "wxAccessToken")
    public String generateWxAccessToken() {
        return "微信AccessToken";
    }
}
