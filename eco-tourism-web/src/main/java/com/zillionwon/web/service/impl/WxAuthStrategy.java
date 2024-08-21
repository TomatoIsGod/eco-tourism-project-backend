package com.zillionwon.web.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zillionwon.common.core.constant.UserStatus;
import com.zillionwon.common.core.domain.model.WxLoginBody;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.core.util.MapstructUtils;
import com.zillionwon.common.core.util.ValidatorUtils;
import com.zillionwon.common.wxapi.model.Code2Session;
import com.zillionwon.web.domain.User;
import com.zillionwon.web.domain.vo.LoginVO;
import com.zillionwon.web.domain.vo.UserVO;
import com.zillionwon.common.wxapi.exception.WxApiException;
import com.zillionwon.web.service.IAuthStrategy;
import com.zillionwon.web.service.IUserService;
import com.zillionwon.common.wxapi.service.WxApiService;
import com.zillionwon.web.service.RegisterService;
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
    private final RegisterService registerService;

    @Autowired
    public WxAuthStrategy(WxApiService wxApiService, IUserService userService, RegisterService registerService) {
        this.wxApiService = wxApiService;
        this.userService = userService;
        this.registerService = registerService;
    }

    @Override
    public LoginVO login(String body) {
        WxLoginBody loginBody = ValidatorUtils.validate(JsonUtils.parseObject(body, WxLoginBody.class));

        // 将 wxCode 转为 openId
        Code2Session code2Session = wxApiService.code2Session(Objects.requireNonNull(loginBody).getWxCode());
        String openId = code2Session.getOpenId();

        // 从数据库获取用户信息，如果不存在则自动注册
        UserVO userVO = getUserByOpenId(openId);

        // 执行登录操作
        StpUtil.login(userVO.getUserId());

        // 返回 token 和 openId
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(StpUtil.getTokenValue());
        loginVO.setOpenId(openId);
        return loginVO;
    }

    private UserVO getUserByOpenId(String openId) {
        // 使用 openId 查询绑定用户 如未绑定用户 则根据业务自行处理 例如 创建默认用户
        UserVO userVO = userService.selectUserByOpenId(openId);
        if (ObjectUtil.isNull(userVO)) {
            // 用户不存在
            log.info("登录用户：{} 不存在.", openId);
            // 创建用户
            userVO.setOpenId(openId);
            registerService.register(MapstructUtils.convert(userVO, User.class));
        } else if (UserStatus.DISABLE.getCode().equals(userVO.getStatus())) {
            // 用户已被停用
            log.info("登录用户：{} 已被停用.", openId);
            throw new WxApiException("500", "用户已停用");
        }
        return userVO;
    }
}
