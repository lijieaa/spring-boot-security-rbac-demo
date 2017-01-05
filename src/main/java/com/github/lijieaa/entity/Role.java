package com.github.lijieaa.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * 角色实体
 */
@Entity
public class Role extends Base{

    private String useId;
    private String roleName;
    private String roleDescription;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id")
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    private Collection<Authority> authorities;


    @Basic
    @Column(name = "use_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }


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
