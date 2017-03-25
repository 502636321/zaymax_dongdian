package com.zaymax.dongdian.domain;

import com.google.common.collect.Sets;
import com.zaymax.dongdian.domain.enums.CfgAuthorityType;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * 系统权限
 * Created by soy50 on 2017/3/3.
 */

@Entity
@Table(name = "SYS_AUTHORITY")
public class SysAuthority extends BaseDomain implements GrantedAuthority {

//    系统管理主页
    public static final String ROLE_SystemController$indexBasic = "ROLE_SystemController$indexBasic";
//    系统参数主页
    public static final String ROLE_PropertiesController$indexProperties = "ROLE_PropertiesController$indexProperties";
//    基础配置主页
    public static final String ROLE_BasicController$indexBasic = "ROLE_BasicController$indexBasic";

//    系统后台信息管理
    public static final String ROLE_ACTUATOR = "ROLE_ACTUATOR";

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHORITY", unique = true, nullable = false)
    private String authority;

    @Enumerated
    @Column(name = "AUTHORITY_TYPE")
    private CfgAuthorityType authorityType;

    @OneToMany(mappedBy = "authority")
    private Set<LinkRoleAuthority> roleAuthorities = Sets.newHashSet();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public CfgAuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(CfgAuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public Set<LinkRoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(Set<LinkRoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }
}
