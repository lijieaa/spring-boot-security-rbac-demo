package com.github.lijieaa.dao;

import com.github.lijieaa.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户Repository
 */
public interface UserRepository extends CrudRepository<User,String> {

    /**
     * 查询用户
     * @param username 用户名
     * @return
     */
    public User findUserByUsername(String username);
}
