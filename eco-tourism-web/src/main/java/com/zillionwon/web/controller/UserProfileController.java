package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.common.core.util.MapstructUtils;
import com.zillionwon.common.satoken.util.LoginHelper;
import com.zillionwon.web.domain.bo.UserBO;
import com.zillionwon.web.domain.bo.UserProfileBO;
import com.zillionwon.web.domain.vo.UserVO;
import com.zillionwon.web.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户个人信息接口
 *
 * @author InwardFlow
 */

@Validated
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * 获取用户个人信息
     *
     * @return 用户个人信息
     */
    @GetMapping
    public R<UserVO> getProfile() {
        UserVO profile = userProfileService.getUserProfile(LoginHelper.getUserId());
        if (profile == null) {
            return R.fail("未获取到用户个人信息");
        } else {
            return R.ok(profile);
        }
    }

    /**
     * 更新用户个人信息
     * @param bo 用户信息
     * @return 是否成功
     */
    @PutMapping
    public R<Void> updateProfile(UserProfileBO bo) {
        bo.setUserId(LoginHelper.getUserId());
        UserBO userBO = MapstructUtils.convert(bo, UserBO.class);
        if (userProfileService.updateUserProfile(Objects.requireNonNull(userBO)) == 1) {
            return R.ok();
        }
        return R.fail("修改个人信息异常，请联系管理员");
    }
}
