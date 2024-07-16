package com.zillionwon.web.domain;

import com.zillionwon.web.constant.GrantType;

/**
 * 用户登录对象
 *
 * @author InwardFlow
 */
public class LoginBody {

    /**
     * 用户授权令牌
     */
    private String token;

    /**
     * 授权类型 (小程序, Web 端等)
     */
    private GrantType grantType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }
}
