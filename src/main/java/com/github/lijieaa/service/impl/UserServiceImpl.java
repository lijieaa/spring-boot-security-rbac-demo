package com.github.lijieaa.service.impl;

import com.github.lijieaa.dao.UserRepository;
import com.github.lijieaa.entity.User;
import com.github.lijieaa.service.BaseService;
import com.github.lijieaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户业务实现类
 */
@Component
public class UserServiceImpl extends BaseService<User,String> implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
