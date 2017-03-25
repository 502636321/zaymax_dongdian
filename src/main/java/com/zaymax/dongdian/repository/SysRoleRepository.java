package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by huiquan on 2017/3/2.
 */
public interface SysRoleRepository extends JpaRepository<SysRole, String>, JpaSpecificationExecutor<SysRole> {

    @Transactional
    @Modifying
    @Query(value = "update SysRole t0 set t0.deleted = true where t0.id in (?1)")
    int delete(List<String> strings);

    Optional<SysRole> findTopByNameAndDeletedFalse(String name);

    /**
     * 返回所有未删除的角色
     * @return
     */
    List<SysRole> findByDeletedFalse();

    /**
     * 根据ID集合查询角色
     * @param strings
     * @return
     */
    List<SysRole> findByIdIn(List<String> strings);

}
