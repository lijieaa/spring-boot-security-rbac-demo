package com.github.lijieaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 所有业务类的父类
 */
public class BaseService<T,ID extends Serializable> {

    @Autowired
    CrudRepository<T,ID> crudRepository;

    /**
     * 添加,更新实体
     * @param t 实体对象
     * @return
     */
    public T add(T t){
        return crudRepository.save(t);
    };


    /**
     * 删除实体
     * @param id 实体对象
     * @return
     */
    public void delete(ID id){
        crudRepository.delete(id);
    };


    /**
     * 获取实体
     * @param id 实体对象
     * @return
     */
    public T get(ID id){
        return crudRepository.findOne(id);
    }

    /**
     * 获取所有实体
     * @param
     * @return
     */
    public Iterable<T> findAll(){
        return crudRepository.findAll();
    }
}
