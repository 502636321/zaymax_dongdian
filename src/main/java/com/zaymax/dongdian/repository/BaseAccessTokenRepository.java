package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.domain.BaseAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Created by huiquan on 2017/3/7.
 */
public interface BaseAccessTokenRepository extends JpaRepository<BaseAccessToken, String> {
    Optional<BaseAccessToken> findTopByCreatedDateGreaterThan(Date date);
}
