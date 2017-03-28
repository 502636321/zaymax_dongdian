package com.zaymax.dongdian.service;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.BaseEmployer;
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

    @Transactional(readOnly = true)
    List<BaseCountry> findTopNameByCountry(String name);
    //---------国家-结束---------//
    //---------雇主-开始---------//
    /**
     * 根据ID删除学历
     * @param id
     * @return
     */
    @Transactional
    int deleteEmployer(String id);

    BaseCountry autoCreateCountry(String name);

    /**
     * 分页查询国家
     * @param pageable
     * @param employer
     * @return
     */
    @Transactional(readOnly = true)
    Page<BaseEmployer> findEmployerPage(Pageable pageable, BaseEmployer employer);

    @Transactional(readOnly = true)
    BaseEmployer findEmployer(String id);

    @Transactional
    BaseEmployer editEmployer(String id, BaseEmployer employer);

    @Transactional
    BaseEmployer saveEmployer(BaseEmployer employer);

    @Transactional(readOnly = true)
    List<BaseEmployer> findAllEmployer();

    //---------雇主-结束---------//
}
