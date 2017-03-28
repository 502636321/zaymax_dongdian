package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.BaseCountry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by huiquan on 2017/3/25.
 */
public class BaseCountryRepositoryTest extends ZaymaxDongdianApplicationTests {
    @Autowired
    private BaseCountryRepository countryRepository;

    @Test
    public void save() throws Exception {
        for (int i = 0; i < 20; i++) {
            BaseCountry country = new BaseCountry();
            country.setName(UUID.randomUUID().toString().substring(0, 4).toUpperCase());
            countryRepository.save(country);
        }
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(countryRepository.findAll().size());
    }

    @Test
    public void findTop20ByNameLikeAndDeletedFalse() throws Exception {

    }
}