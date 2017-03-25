package com.zaymax.dongdian.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密器
 * Created by soy50 on 2017/3/22.
 */
@Component
public class PasswordEncoder extends BCryptPasswordEncoder {
    public static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        LOGGER.debug("密码加密器加密[{}]", rawPassword);
        return super.encode(rawPassword);
    }
}
