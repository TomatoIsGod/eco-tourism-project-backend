package com.zillionwon.web.service;

import com.zillionwon.web.domain.User;
import com.zillionwon.web.domain.vo.UserVO;
import com.zillionwon.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户个人信息业务层处理
 *
 * @author InwardFlow
 */

@Service
public class UserProfileService {
    @Autowired
    private UserMapper userMapper;

    public UserVO getUserProfile(Long userId) {
        return userMapper.selectVoById(userId);
    }

    public Integer updateUserProfile(User userBO) {
        return userMapper.updateById(userBO);
    }
}
