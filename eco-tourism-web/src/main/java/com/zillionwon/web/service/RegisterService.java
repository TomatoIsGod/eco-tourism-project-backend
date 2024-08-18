package com.zillionwon.web.service;

import com.zillionwon.web.domain.User;
import com.zillionwon.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户注册业务层处理
 *
 * @author InwardFlow
 */

@Service
public class RegisterService {

    @Autowired
    private UserMapper mapper;

    public int register(User user) {
        return mapper.insert(user);
    }
}
