package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.SysProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by soy50 on 2017/3/26.
 */
public class SysPropertiesRepositoryTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private SysPropertiesRepository propertiesRepository;

    @Test
    public void findTopByDeletedFalseOrderByLastModifiedDateDesc() throws Exception {
        Optional<SysProperties> propertiesOptional = propertiesRepository.findTopByDeletedFalseOrderByLastModifiedDateDesc();
        System.out.println(propertiesOptional.isPresent());
    }

    @Test
    public void findTop20ByDeletedFalseOrderByCreatedDateDesc() throws Exception {
    }

}