package com.zillionwon.web.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.zillionwon.web.config.WeChatProperties;
import com.zillionwon.web.exception.WxApiException;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序 api 服务
 * @author InwardFlow
 */
@Service
public class WxApiService {
    public final WeChatProperties weChatProperties;
    @Autowired
    public WxApiService(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }

    /**
     * 获取 openId 用户唯一标识
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return JSON 字符串
     */
    public String code2Session(String wxCode) {

        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", weChatProperties.getAppId());
        requestUrlParam.put("secret", weChatProperties.getAppSecret());
        requestUrlParam.put("js_code", wxCode);
        requestUrlParam.put("grant_type", "authorization_code");
        return validate(HttpRequest.post(requestUrl)
                .body(JSONUtil.toJsonStr(requestUrlParam))
                .timeout(5000)
                .execute()
                .body());
    }

    /**
     * 获取用户手机号
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return 手机号
     */
    public String code2PhoneNumber(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + getAccessToken();
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("code", wxCode);
        return validate(JSONUtil.parseObj(HttpRequest.post(requestUrl)
                .body(JSONUtil.toJsonStr(requestUrlParam))
                .timeout(5000)
                .execute()
                .body()).getStr("phone_number"));
    }

    /**
     * 获取接口调用凭据
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">微信官方开发者文档</a>
     * @return access_token
     */
    public String getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", weChatProperties.getAppId());
        requestUrlParam.put("secret", weChatProperties.getAppSecret());
        requestUrlParam.put("grant_type", "client_credential");    // 默认参数
        return validate(JSONUtil.parseObj(HttpRequest.post(requestUrl)
                .body(JSONUtil.toJsonStr(requestUrlParam))
                .timeout(5000)
                .execute()
                .body()).getStr("access_token"));
    }

    /**
     * 微信接口返回值校验器
     * @param body 微信接口返回的 JSON
     * @return 成功返回 body，失败则报错
     */
    private static String validate(String body) {
        String code = JSONUtil.parseObj(body).getStr("errcode");
        if (!("0".equals(code) || code == null) || StringUtils.isBlank(body)) {
            String msg = JSONUtil.parseObj(body).getStr("errmsg");
            throw new WxApiException(code, msg);
        }
        return body;
    }
}
