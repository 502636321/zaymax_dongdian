package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.BaseEmployer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by huiquan on 2017/3/28.
 */
public class BaseEmployerRepositoryTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private BaseEmployerRepository employerRepository;

    @Test
    public void findByDeletedFalse() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void save() throws Exception {
        //tets
        for (int i = 0; i < 50; i++) {
            BaseEmployer employer = new BaseEmployer();
            employer.setName("雇主[" + i + "]");

            employerRepository.save(employer);
        }
    }
}