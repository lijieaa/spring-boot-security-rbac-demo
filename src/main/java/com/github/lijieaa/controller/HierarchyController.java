package com.github.lijieaa.controller;

import com.github.lijieaa.entity.Hierarchy;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理继承控制器
 */
@RequestMapping("hierarchy")
@RestController
public class HierarchyController extends BaseController<Hierarchy,String>{

    /**
     * 添加权限继承
     * @param Hierarchy 权限继承实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Hierarchy addHierarchy(@RequestBody Hierarchy Hierarchy){
        return this.add(Hierarchy);
    }

    /**
     * 更新权限继承
     * @param Hierarchy 权限继承实体
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Hierarchy updateHierarchy(@RequestBody Hierarchy Hierarchy){
        return this.add(Hierarchy);
    }


    /**
     * 删除权限继承
     * @param id 权限继承id
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteHierarchy(@RequestParam String id){
        this.delete(id);
    }
}
