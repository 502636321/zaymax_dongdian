package com.zaymax.dongdian.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色权限中间表
 * Created by soy50 on 2017/3/3.
 */

@Entity
@Table(name = "LINK_ROLE_AUTHORITY")
public class LinkRoleAuthority extends BaseDomain {

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private SysRole role;

    @ManyToOne
    @JoinColumn(name = "AUTHORITY_ID")
    private SysAuthority authority;

    public LinkRoleAuthority() {
    }

    public LinkRoleAuthority(SysRole role, SysAuthority authority) {
        this.role = role;
        this.authority = authority;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(SysAuthority authority) {
        this.authority = authority;
    }
}
