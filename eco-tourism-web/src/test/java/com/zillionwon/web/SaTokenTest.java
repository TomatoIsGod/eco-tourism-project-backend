package com.zillionwon.web;

import cn.dev33.satoken.stp.StpUtil;
import com.zillionwon.common.core.domain.R;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.satoken.util.LoginHelper;
import com.zillionwon.web.domain.vo.LoginVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Sa-Token 鉴权单元测试")
public class SaTokenTest {

    @Test
    @DisplayName("过期 Token 测试")
    public void outdatedTokenTest() {
        LoginVO loginVO = new LoginVO();
        // 执行登录操作
        StpUtil.login(1);
        loginVO.setToken(StpUtil.getTokenValue());
        StpUtil.logout();
        System.out.println(R.ok(StpUtil.getLoginIdByToken(loginVO.getToken())));
    }

    @Test
    @DisplayName("无效 Token 测试")
    public void invalidTokenTest() {

    }

    @Test
    @DisplayName("有效 Token 测试")
    public void successTokenTest() {
        LoginVO loginVO = new LoginVO();
        // 执行登录操作
        StpUtil.login(1);
        loginVO.setToken(StpUtil.getTokenValue());
        System.out.println(R.ok(JsonUtils.toJsonString(loginVO)));
        System.out.println(StpUtil.getLoginId());
        System.out.println(LoginHelper.getLoginUser(loginVO.getToken()));
    }

}
