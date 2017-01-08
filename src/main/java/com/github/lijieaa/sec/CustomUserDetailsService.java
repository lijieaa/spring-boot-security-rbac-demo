package com.github.lijieaa.sec;

import com.github.lijieaa.entity.Authority;
import com.github.lijieaa.entity.Role;
import com.github.lijieaa.entity.User;
import com.github.lijieaa.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * 用户细节服务
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userService.findUserByUsername(username);

        /*Collection<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();

        Collection<Role> roles=user.getRoles();

        for (Role role : roles) {

           Collection<Authority> authorities=role.getAuthorities();

            for (Authority authority : authorities) {

                CustomGrantedAuthority customGrantedAuthority=new CustomGrantedAuthority(authority.getAuthorityName());

                grantedAuthorities.add(customGrantedAuthority);
            }
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user.getUsername(),user.getPassword(),true, true, true, true,grantedAuthorities);*/

        Collection<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();

        Set<Authority> authorities = user.getAuthorities();

        for (Authority authority : authorities) {
            CustomGrantedAuthority customGrantedAuthority=new CustomGrantedAuthority(authority.getAuthorityName());

            grantedAuthorities.add(customGrantedAuthority);
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user.getUsername(),user.getPassword(),true, true, true, true,grantedAuthorities);
        return customUserDetails;
    }
}
