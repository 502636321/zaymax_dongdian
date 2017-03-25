package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by huiquan on 2017/2/27.
 */
public interface SysUserRepository extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {

    @Transactional
    @Modifying
    @Query(value = "update SysUser t0 set t0.deleted = true where t0.id in (?1)")
    int delete(List<String> strings);

    Optional<SysUser> findTopByUsernameAndDeletedFalse(String username);

    @Transactional
    @Modifying
    @Query(value = "update SysUser t0 set t0.locked = (case when t0.locked = true then false else true end) where t0.id = ?1")
    int lockedOrUnlockedUser(String id);
}
