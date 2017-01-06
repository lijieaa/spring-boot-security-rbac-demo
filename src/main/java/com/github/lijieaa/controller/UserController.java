package com.github.lijieaa.controller;

import com.github.lijieaa.entity.User;
import com.github.lijieaa.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RequestMapping("user")
@RestController
public class UserController extends BaseController<User,String>{

    @Autowired
    UserServiceImpl userService;

    /**
     * 添加用户
     * @param user 用户实体
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADD_USER')")
    @Secured("ROLE_ADD_USER")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return this.add(user);
    }

    /**
     * 更新用户
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user){
        return this.add(user);
    }


    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam String id){
        this.delete(id);
    }

    /**
     * 查询用户
     * @param username 用户名
     * @return
     */
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public User findUserByUsername(@RequestParam String username){
        return userService.findUserByUsername(username);
    }
}
