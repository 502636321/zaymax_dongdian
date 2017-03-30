package com.zaymax.dongdian.service;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.SysExpatriate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;

/**
 * Created by soy50 on 2017/3/26.
 */
public interface ExpatriateService {
    /**
     * 根据条件分页查询外派人员
     * @param pageable
     * @param expatriate
     * @return
     */
    @Transactional(readOnly = true)
    Page<SysExpatriate> findExpatriatePage(Pageable pageable, SysExpatriate expatriate);

    /**
     * 增加外派人员操作
     * @param expatriate
     * @return
     */
    @Transactional
    SysExpatriate saveExpatriate(SysExpatriate expatriate);

    /**
     * 根据ID查询外派人员
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    SysExpatriate findExpatriate(String id);

    /**
     * 编辑外派人员操作
     * @param id
     * @param expatriate
     * @return
     */
    @Transactional
    SysExpatriate editExpatriate(String id, SysExpatriate expatriate);

    @Transactional
    int deleteExpatriate(String id);

    @Transactional(readOnly = true)
    void exportExpatriate(ServletOutputStream outputStream, SysExpatriate expatriate);
}
