package com.github.lijieaa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class Base implements Serializable {

    private String id;

    private Timestamp createTime;

    private Timestamp updateTime;


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = true, updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false, insertable = true, updatable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public Timestamp getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    @PrePersist
    protected void onCreate() {
        updateTime=createTime=new Timestamp((new Date()).getTime());
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime =new Timestamp((new Date()).getTime());
    }

}