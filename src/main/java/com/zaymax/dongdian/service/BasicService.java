package com.zaymax.dongdian.service;

import com.zaymax.dongdian.domain.BaseCountry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huiquan on 2017/3/2.
 */
public interface BasicService {
    //---------国家-开始---------//
    /**
     * 根据ID删除学历
     * @param id
     * @return
     */
    @Transactional
    int deleteCountry(String id);

    /**
     * 分页查询国家
     * @param pageable
     * @param country
     * @return
     */
    @Transactional(readOnly = true)
    Page<BaseCountry> findCountryPage(Pageable pageable, BaseCountry country);

    @Transactional(readOnly = true)
    BaseCountry findCountry(String id);

    @Transactional
    BaseCountry editCountry(String id, BaseCountry country);

    @Transactional
    BaseCountry saveCountry(BaseCountry country);

    @Transactional(readOnly = true)
    List<BaseCountry> findAllCountry();
    //---------国家-结束---------//


}
