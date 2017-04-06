package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by soy50 on 2017/3/3.
 */
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, String>, JpaSpecificationExecutor<SysAuthority> {
    @Transactional
    @Modifying
    @Query(value = "update SysAuthority t0 set t0.deleted = true where t0.id in (?1)")
    int delete(List<String> strings);

    List<SysAuthority> findByRoleAuthoritiesRoleId(String id);

    /**
     * 根据权限标识忽略大小写查询权限
     * @param authorization
     * @return
     */
    SysAuthority findByDeletedFalseAndAuthorityIgnoreCase(String authorization);

    /**
     * 根据用户ID查询所拥有的权限
     * @param userId
     * @return
     */
    List<SysAuthority> findDistinctSysAuthorityByDeletedFalseAndRoleAuthoritiesRoleUserRolesUserId(String userId);

    /**
     * 查询所有未删除的权限
     * @return
     */
    List<SysAuthority> findByDeletedFalse();

    /**
     * 根据权限ID查询集合
     * @param strings
     */
    List<SysAuthority> findByIdIn(List<String> strings);

    SysAuthority findByAuthority(String authority);
}
