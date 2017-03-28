package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.BaseEmployer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 雇主
 * Created by soy50 on 2017/3/27.
 */
public interface BaseEmployerRepository extends JpaRepository<BaseEmployer, String>, JpaSpecificationExecutor<BaseEmployer> {

    List<BaseEmployer> findByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE BaseEmployer t0 SET t0.deleted = true WHERE t0.id IN (?1)")
    int delete(List<String> strings);
}
