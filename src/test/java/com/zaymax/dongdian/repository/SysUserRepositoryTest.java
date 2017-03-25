package com.zaymax.dongdian.repository;

import com.zaymax.dongdian.ZaymaxDongdianApplicationTests;
import com.zaymax.dongdian.domain.SysUser;
import com.zaymax.dongdian.security.PasswordEncoder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by huiquan on 2017/3/25.
 */
public class SysUserRepositoryTest extends ZaymaxDongdianApplicationTests {

    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save() throws Exception {
//        SysUser user = new SysUser();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//
//        userRepository.save(user);
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findTopByUsernameAndDeletedFalse() throws Exception {

    }

    @Test
    public void lockedOrUnlockedUser() throws Exception {

    }

}