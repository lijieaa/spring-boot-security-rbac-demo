package com.github.lijieaa.dao;

import com.github.lijieaa.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户Repository
 */
public interface UserRepository extends CrudRepository<User,String> {
}
