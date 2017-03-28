package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.SysExpatriate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by soy50 on 2017/3/27.
 */
public class SysExpatriateRepositoryTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private SysExpatriateRepository expatriateRepository;

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void save() throws Exception {
        for (int i = 0; i < 70; i++) {
            SysExpatriate expatriate = new SysExpatriate();
            expatriate.setName(UUID.randomUUID().toString().substring(0, 6).toUpperCase());

            expatriateRepository.save(expatriate);
        }
    }

    @Test
    public void countDistinctByIdIsNotNull() throws Exception {
        System.out.println(expatriateRepository.countDistinctByIdIsNotNull());
    }
}