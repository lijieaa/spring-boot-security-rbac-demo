package com.github.lijieaa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * 用户实体
 */
@Entity
public class User extends Base{

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "use_id") }, inverseJoinColumns = { @JoinColumn(name = "aut_id") })
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    private Set<Authority> authorities;

    /*private Collection<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "use_id")
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }*/

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
