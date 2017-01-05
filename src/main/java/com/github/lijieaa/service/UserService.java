package com.github.lijieaa.service;

import com.github.lijieaa.entity.User;

/**
 * 用户业务
 */
public interface UserService {

    /**
     * 查询用户
     * @param username 用户名
     * @return
     */
    public User findUserByUsername(String username);

}
