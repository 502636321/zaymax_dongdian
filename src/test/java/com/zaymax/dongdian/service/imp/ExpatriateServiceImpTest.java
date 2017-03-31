package com.zaymax.dongdian.service.imp;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.service.ExpatriateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;

/**
 * Created by huiquan on 2017/3/31.
 */
public class ExpatriateServiceImpTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private ExpatriateService expatriateService;

    @Test
    public void findExpatriatePage() throws Exception {
        expatriateService.findExpatriatePage(new PageRequest(0, 900), null).getContent().forEach(sysExpatriate -> {
//            System.out.println(sysExpatriate.getId());
            //开始测试是否连接所有的类
            if (sysExpatriate.getSocialInsurance() != null) {
                System.out.println(sysExpatriate.getSocialInsurance().getPersonalCode());
            }
        });
    }

}