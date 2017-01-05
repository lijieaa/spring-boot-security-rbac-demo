package com.github.lijieaa.controller;

import com.github.lijieaa.entity.Role;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RequestMapping("role")
@RestController
public class RoleController extends BaseController<Role,String>{

    /**
     * 添加用户
     * @param Role 用户实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Role addRole(@RequestBody Role Role){
        return this.add(Role);
    }

    /**
     * 更新用户
     * @param Role 用户实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Role updateRole(@RequestBody Role Role){
        return this.add(Role);
    }


    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteRole(@RequestParam String id){
        this.delete(id);
    }
}
