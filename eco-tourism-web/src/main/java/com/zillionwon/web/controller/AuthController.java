package com.zillionwon.web.controller;

import cn.hutool.json.JSONUtil;
import com.zillionwon.common.core.util.ValidatorUtils;
import com.zillionwon.web.constant.GrantType;
import com.zillionwon.web.domain.LoginBody;
import com.zillionwon.web.domain.R;
import com.zillionwon.web.domain.vo.LoginVO;
import com.zillionwon.web.service.IAuthStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 登录认证
 *
 * @author InwardFlow
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 登录认证接口
     * @param body 响应主体
     * @return 登录验证信息
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody String body) {
        // 反序列化 LoginBody
        LoginBody loginBody = ValidatorUtils.validate(JSONUtil.toBean(body, LoginBody.class));
        try {
            GrantType grantType = Objects.requireNonNull(loginBody.getGrantType());
            return R.ok(IAuthStrategy.login(body, grantType));
        } catch (NullPointerException e) {
            log.info("授权错误: 不存在该认证类型");
            return R.fail("不存在该认证类型");
        }
    }
}
