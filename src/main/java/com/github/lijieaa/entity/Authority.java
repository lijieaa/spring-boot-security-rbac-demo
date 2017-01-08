package com.github.lijieaa.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限实体
 */
@Entity
public class Authority extends Base{

    private String authorityName;
    private String authorityDescription;

    @ManyToMany
    //@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "aut_id") }, inverseJoinColumns = { @JoinColumn(name = "use_id") })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    private Set<User> users;
   /* private String rolId;

    @Basic
    @Column(name = "rol_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }*/

    @Basic
    @Column(name = "authority_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getAuthorityDescription() {
        return authorityDescription;
    }

    public void setAuthorityDescription(String authorityDescription) {
        this.authorityDescription = authorityDescription;
    }

}
