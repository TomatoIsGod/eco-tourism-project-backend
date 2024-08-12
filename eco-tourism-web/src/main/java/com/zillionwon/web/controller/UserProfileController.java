package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.common.satoken.util.LoginHelper;
import com.zillionwon.web.domain.User;
import com.zillionwon.web.domain.vo.UserVO;
import com.zillionwon.web.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户个人信息接口
 *
 * @author InwardFlow
 */

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
        return R.ok(userProfileService.getUserProfile(LoginHelper.getUserId()));
    }

    /**
     * 更新用户个人信息
     * @param bo 用户信息
     * @return 修改的行数
     */
    @PutMapping
    public R<UserVO> updateProfile(User bo) {
        bo.setUserId(LoginHelper.getUserId());
        if (userProfileService.updateUserProfile(bo) == 1) {
            return R.ok();
        }
        return R.fail("修改个人信息异常，请联系管理员");
    }
}
