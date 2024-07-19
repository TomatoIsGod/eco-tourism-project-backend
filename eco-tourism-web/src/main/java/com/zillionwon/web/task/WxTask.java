package com.zillionwon.web.task;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.zillionwon.web.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.zillionwon.common.core.util.ValidatorUtils.validate;

/**
 * 微信小程序定时任务类
 *
 * @author InwardFlow
 */

@Slf4j
@Component
public class WxTask {

    private final WxConfig wxConfig;

    public WxTask(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 定时任务：获取接口调用凭据
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">微信官方开发者文档</a>
     */
    // 首次运行延迟 5 秒钟，2 小时重新获取一次
    // TODO: 使用 xxl-job 重写
    @Scheduled(initialDelay = 5000, fixedDelay = 7200 * 1000)
    public void getAccessTokenTask() {
        log.info("正在获取 wxAccessToken, 当前时间: {}", System.currentTimeMillis());
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", wxConfig.getAppId());
        requestUrlParam.put("secret", wxConfig.getAppSecret());
        requestUrlParam.put("grant_type", "client_credential");    // 默认参数
        wxConfig.setAccessToken(validate(JSONUtil
                .parseObj(HttpRequest.post(requestUrl)
                .body(JSONUtil.toJsonStr(requestUrlParam))
                .timeout(5000)
                .execute()
                .body()).getStr("access_token")));
        log.info("刷新 wxAccessToken 成功!");
    }
}
