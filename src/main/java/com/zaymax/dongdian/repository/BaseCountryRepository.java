package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.BaseCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 国家
 * Created by huiquan on 2017/3/25.
 */
public interface BaseCountryRepository extends JpaRepository<BaseCountry, String>, JpaSpecificationExecutor<BaseCountry> {
    List<BaseCountry> findByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE BaseCountry t0 SET t0.deleted = true WHERE t0.id IN (?1)")
    int delete(List<String> strings);
}
