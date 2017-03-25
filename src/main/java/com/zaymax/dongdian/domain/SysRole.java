package com.zaymax.dongdian.domain;

import com.google.common.collect.Sets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 角色
 * Created by huiquan on 2017/3/2.
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole extends BaseDomain {
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 角色所拥有的权限集合
     */
    @OneToMany(mappedBy = "role")
    private Set<LinkRoleAuthority> roleAuthorities = Sets.newHashSet();

    /**
     * 拥有该角色的用户集合
     */
    @OneToMany(mappedBy = "role")
    private Set<LinkUserRole> userRoles = Sets.newHashSet();

    public SysRole() {
    }

    public SysRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LinkRoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(Set<LinkRoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }

    public Set<LinkUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<LinkUserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
