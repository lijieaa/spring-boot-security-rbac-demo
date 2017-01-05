package com.github.lijieaa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 角色实体
 */
@Entity
public class Role extends Base{
    private String useId;
    private String roleName;
    private String roleDescription;


    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }


}
