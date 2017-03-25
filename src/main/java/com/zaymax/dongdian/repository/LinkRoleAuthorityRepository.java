package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.LinkRoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soy50 on 2017/3/24.
 */
public interface LinkRoleAuthorityRepository extends JpaRepository<LinkRoleAuthority, String> {
    /**
     * 根据角色ID查询角色权限关联记录
     * @param id
     * @return
     */
    List<LinkRoleAuthority> findByDeletedFalseAndRoleId(String id);

    @Transactional
    @Modifying
    @Query(value = "update LinkRoleAuthority t0 set t0.deleted = true where t0.role.id in (?1)")
    int deleteByRoleId(ArrayList<String> strings);
}
