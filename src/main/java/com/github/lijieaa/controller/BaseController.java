package com.github.lijieaa.controller;

import com.github.lijieaa.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 所有控制器的父类
 */
public class BaseController<T,ID extends Serializable> {

    @Autowired
    BaseService<T,ID> baseService;

    /**
     * 添加业务对象
     * @param t 业务对象
     * @return
     */
    public T add(T t){
        return baseService.add(t);
    }

    /**
     * 删除实体
     * @param id 实体对象
     * @return
     */
    public void delete(ID id){
        baseService.delete(id);
    };
}
