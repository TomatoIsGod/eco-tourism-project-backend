package com.zillionwon.common.wxapi.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.core.util.ValidatorUtils;
import com.zillionwon.common.wxapi.config.WxConfig;
import com.zillionwon.common.wxapi.model.Code2Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序 api 服务
 *
 * @author InwardFlow
 */

@Service
public class WxApiService {
    private final WxConfig wxConfig;
    @Autowired
    public WxApiService(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 获取 openId 用户唯一标识
     *
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return JSON 字符串
     */
    public Code2Session code2Session(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", wxConfig.getAppId());
        requestUrlParam.put("secret", wxConfig.getAppSecret());
        requestUrlParam.put("js_code", wxCode);
        requestUrlParam.put("grant_type", "authorization_code");
        return ValidatorUtils.validate(JsonUtils.parseObject(HttpRequest.post(requestUrl)
                .header("Content-Type", "multipart/form-data")
                .form(requestUrlParam)
                .timeout(5000)
                .execute()
                .body(), Code2Session.class));
    }

    /**
     * 获取用户手机号
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return 手机号
     */
    public String code2PhoneNumber(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + wxConfig.getAccessToken();
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("code", wxCode);
        return ValidatorUtils.validate(JSONUtil.parseObj(HttpRequest.post(requestUrl)
                .header("Content-Type", "multipart/form-data")
                .body(JSONUtil.toJsonStr(requestUrlParam))
                .timeout(5000)
                .execute()
                .body()).getStr("phone_number"));
    }
}
