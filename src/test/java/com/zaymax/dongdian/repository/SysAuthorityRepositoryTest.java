package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.SysAuthority;
import com.zaymax.dongdian.domain.enums.CfgAuthorityType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by soy50 on 2017/3/24.
 */
public class SysAuthorityRepositoryTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private SysAuthorityRepository authorityRepository;

    @Test
    public void save() throws Exception {
        SysAuthority authority = authorityRepository.findByDeletedFalseAndAuthorityIgnoreCase(SysAuthority.ROLE_SystemController$indexBasic);
        //
        if (authority == null) {
            authority = new SysAuthority();
            authority.setAuthorityType(CfgAuthorityType.MENU);
            authority.setAuthority(SysAuthority.ROLE_SystemController$indexBasic);
            authorityRepository.save(authority);
        }
        //
        authority = authorityRepository.findByDeletedFalseAndAuthorityIgnoreCase(SysAuthority.ROLE_PropertiesController$indexProperties);
        if (authority == null) {
            authority = new SysAuthority();
            authority.setAuthorityType(CfgAuthorityType.MENU);
            authority.setAuthority(SysAuthority.ROLE_PropertiesController$indexProperties);
            authorityRepository.save(authority);
        }
        //
        authority = authorityRepository.findByDeletedFalseAndAuthorityIgnoreCase(SysAuthority.ROLE_BasicController$indexBasic);
        if (authority == null) {
            authority = new SysAuthority();
            authority.setAuthorityType(CfgAuthorityType.MENU);
            authority.setAuthority(SysAuthority.ROLE_BasicController$indexBasic);
            authorityRepository.save(authority);
        }
        authority = authorityRepository.findByDeletedFalseAndAuthorityIgnoreCase(SysAuthority.ROLE_ACTUATOR);
        if (authority == null) {
            authority = new SysAuthority();
            authority.setAuthorityType(CfgAuthorityType.INFO);
            authority.setAuthority(SysAuthority.ROLE_ACTUATOR);
            authorityRepository.save(authority);
        }

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findByRoleAuthoritiesRoleId() throws Exception {

    }

    @Test
    public void findByDeletedFalseAndRoleAuthoritiesRoleUserRolesUserId() throws Exception {
        String adminUserId = "4028b8815afe3c6b015afe3c88530000";
        authorityRepository.findDistinctSysAuthorityByDeletedFalseAndRoleAuthoritiesRoleUserRolesUserId(adminUserId).forEach(sysAuthority -> {
            System.out.println(sysAuthority.getId());
        });
    }
}