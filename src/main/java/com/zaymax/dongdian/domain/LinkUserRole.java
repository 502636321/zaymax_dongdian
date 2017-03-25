package com.zaymax.dongdian.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户跟角色的中间关联表
 * Created by soy50 on 2017/3/24.
 */
@Entity
@Table(name = "LINK_USER_ROLE")
public class LinkUserRole extends BaseDomain {

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private SysUser user;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private SysRole role;

    public LinkUserRole() {
    }

    public LinkUserRole(SysUser user, SysRole role) {
        this.user = user;
        this.role = role;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
