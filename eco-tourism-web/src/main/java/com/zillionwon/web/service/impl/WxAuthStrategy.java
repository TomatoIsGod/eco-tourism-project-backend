package com.zillionwon.web.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zillionwon.common.core.constant.UserStatus;
import com.zillionwon.common.core.domain.model.WxLoginBody;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.core.util.ValidatorUtils;
import com.zillionwon.web.domain.vo.LoginVO;
import com.zillionwon.web.domain.vo.UserVO;
import com.zillionwon.common.wxapi.exception.WxApiException;
import com.zillionwon.web.service.IAuthStrategy;
import com.zillionwon.web.service.IUserService;
import com.zillionwon.common.wxapi.service.WxApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 微信小程序登录授权实现类
 *
 * @author InwardFlow
 */

@Slf4j
@Service("wx" + IAuthStrategy.BASE_NAME)
public class WxAuthStrategy implements IAuthStrategy {
    private final WxApiService wxApiService;
    private final IUserService userService;

    @Autowired
    public WxAuthStrategy(WxApiService wxApiService, IUserService userService) {
        this.wxApiService = wxApiService;
        this.userService = userService;
    }

    @Override
    public LoginVO login(String body) {
        WxLoginBody loginBody = ValidatorUtils.validate(JsonUtils.parseObject(body, WxLoginBody.class));
        String openId = wxApiService.code2Session(Objects.requireNonNull(loginBody).getWxCode());
        LoginVO loginVO = new LoginVO();
        // 从数据库获取用户信息，如果不存在则抛出异常
        UserVO userVO = getUserByOpenid(openId);
        // 执行登录操作
        StpUtil.login(userVO.getUserId());
        loginVO.setToken(StpUtil.getTokenValue());
        loginVO.setOpenId(openId);
        return loginVO;
    }

    private UserVO getUserByOpenid(String openId) {
        // 使用 openId 查询绑定用户 如未绑定用户 则根据业务自行处理 例如 创建默认用户
        UserVO user = userService.selectUserByOpenId(openId);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", openId);
            throw new WxApiException("500", "用户不存在");
            // 用户不存在
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", openId);
            // 用户已被停用
            throw new WxApiException("500", "用户已停用");
        }
        return user;
    }
}
