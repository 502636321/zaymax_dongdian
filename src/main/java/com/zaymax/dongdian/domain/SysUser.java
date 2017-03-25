package com.zaymax.dongdian.domain;

import com.google.common.collect.Lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 系统用户
 * Created by huiquan on 2017/2/27.
 */
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseDomain {

    /**
     * 用户
     */
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<LinkUserRole> roles = Lists.newArrayList();

    /**
     * 用户是否锁定
     */
    @Column(name = "LOCKED")
    private Boolean locked = Boolean.FALSE;

    public SysUser() {
    }

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LinkUserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<LinkUserRole> roles) {
        this.roles = roles;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
