package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.LinkUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by soy50 on 2017/3/24.
 */
public interface LinkUserRoleRepository extends JpaRepository<LinkUserRole, String> {
    @Transactional
    @Modifying
    @Query(value = "update LinkUserRole t0 set t0.deleted = true where t0.id in (?1)")
    int delete(List<String> strings);

    @Transactional
    @Modifying
    @Query(value = "update LinkUserRole t0 set t0.deleted = true where t0.user.id in (?1)")
    int deleteByUserId(List<String> strings);

    List<LinkUserRole> findByDeletedFalseAndUserId(String userId);
}
