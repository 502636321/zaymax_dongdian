package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.SysExpatriate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by soy50 on 2017/3/26.
 */
public interface SysExpatriateRepository extends JpaRepository<SysExpatriate, String>, JpaSpecificationExecutor<SysExpatriate> {
    @Transactional
    @Modifying
    @Query(value = "update SysExpatriate t0 set t0.deleted = true where t0.id in (?1)")
    int delete(List<String> strings);

    /**
     * 统计人员数量
     * @return
     */
    long countDistinctByIdIsNotNull();
}
