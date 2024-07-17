package com.zillionwon.web.service.impl;

import com.zillionwon.common.core.domain.model.WxLoginBody;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.core.util.SpringUtils;
import com.zillionwon.web.domain.vo.LoginVO;
import com.zillionwon.web.service.IAuthStrategy;
import com.zillionwon.web.service.WxApiService;
import org.springframework.stereotype.Service;

/**
 * 微信小程序登录授权实现类
 *
 * @author InwardFlow
 */

@Service("email" + IAuthStrategy.BASE_NAME)
public class WxAuthStrategy implements IAuthStrategy {
    private final WxApiService wxApiService;

    private String wxAccessToken = SpringUtils.getBean("wxAccessToken");
    public WxAuthStrategy(WxApiService wxApiService) {
        this.wxApiService = wxApiService;
    }

    @Override
    public LoginVO login(String body) {
        WxLoginBody loginBody = JsonUtils.parseObject(body, WxLoginBody.class);
        SpringUtils.registerBean("bean", "dd");
//        wxApiService.code2Session()
        // TODO:
        return null;
    }
}
