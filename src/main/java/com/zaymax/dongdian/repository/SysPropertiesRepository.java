package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.SysProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by huiquan on 2017/3/3.
 */
public interface SysPropertiesRepository extends JpaRepository<SysProperties, String> {

    Optional<SysProperties> findTopByDeletedFalseOrderByLastModifiedDateDesc();

    List<SysProperties> findTop20ByDeletedFalseOrderByCreatedDateDesc();
}
