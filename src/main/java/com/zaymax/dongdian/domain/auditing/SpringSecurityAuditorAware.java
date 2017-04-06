package com.zaymax.dongdian.domain.auditing;

import com.zaymax.dongdian.domain.SysUser;
import com.zaymax.dongdian.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by soy50 on 2017/3/27.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<SysUser> {
    public static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityAuditorAware.class);

    @Override
    public SysUser getCurrentAuditor() {
        LOGGER.debug("获取当前登录用户");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return ((SecurityUser)authentication.getPrincipal()).getUser();
    }
}
