package com.zillionwon.web.service;

import com.zillionwon.web.domain.vo.UserVO;

/**
 * 城市 Service 接口
 *
 * @author InwardFlow
 */
public interface IUserService {

    /**
     * 通过 userId 查询用户信息
     */
    UserVO selectUserById(Long userId);

    /**
     * 通过 openId 查询用户信息
     */
    UserVO selectUserByOpenId(String openId);
}
