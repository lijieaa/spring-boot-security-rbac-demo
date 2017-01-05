package com.github.lijieaa.sec;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Administrator on 2017/1/5.
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
