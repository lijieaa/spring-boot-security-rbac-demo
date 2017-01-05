package com.github.lijieaa.sec;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Administrator on 2017/1/5.
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
