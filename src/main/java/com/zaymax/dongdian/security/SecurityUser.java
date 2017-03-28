package com.zaymax.dongdian.security;

import com.zaymax.dongdian.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 系统安全用户（默认跟系统用户绑定）
 * Created by huiquan on 2017/3/22.
 */
public class SecurityUser implements UserDetails {
    /**
     * 关联的系统用户
     */
    private SysUser user;
    /**
     * 拥有的权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(SysUser user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user != null ? user.getPassword() : null;
    }

    @Override
    public String getUsername() {
        return user != null ? user.getPassword() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否未被锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(user.getLocked()) ? false : true;
    }

    /**
     * 是否未失效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
