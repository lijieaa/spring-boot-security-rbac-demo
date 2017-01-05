package com.github.lijieaa.controller;

import com.github.lijieaa.entity.Authority;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器
 */
@RequestMapping("authority")
@RestController
public class AuthorityController extends BaseController<Authority,String>{

    /**
     * 添加权限
     * @param Authority 权限实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Authority addAuthority(@RequestBody Authority Authority){
        return this.add(Authority);
    }

    /**
     * 更新权限
     * @param Authority 权限实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Authority updateAuthority(@RequestBody Authority Authority){
        return this.add(Authority);
    }


    /**
     * 删除权限
     * @param id 权限id
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteAuthority(@RequestParam String id){
        this.delete(id);
    }
}
