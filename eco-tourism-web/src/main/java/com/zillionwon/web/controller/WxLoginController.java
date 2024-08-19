package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序登录相关
 *
 * @author InwardFlow
 */

@RestController
@RequestMapping("/wxLogin")
@CrossOrigin
public class WxLoginController {

    public final WxApiService wxApiService;

    @Autowired
    public WxLoginController(WxApiService wxApiService) {
        this.wxApiService = wxApiService;
    }

    /**
     * 获取微信用户在小程序内部的唯一标识
     * @param code 获取凭证
     * @return openId
     */
    @PostMapping("/getOpenId")
    public R<String> getWxOpenId(String code) {
        // 请求微信 Api 获取用户的 openId 和 sessionKey
        return R.ok("获取成功", wxApiService.code2Session(code));
    }

    /**
     * 获取微信用户的手机号码
     * @param code 获取凭证
     * @return 手机号
     */
    @PostMapping("/getPhoneNumber")
    public R<String> getPhoneNumber(String code) {
        return R.ok("获取成功", wxApiService.code2PhoneNumber(code));
    }
}
