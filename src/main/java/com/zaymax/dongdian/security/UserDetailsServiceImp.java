package com.zaymax.dongdian.security;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.SysUser;
import com.zaymax.dongdian.repository.SysAuthorityRepository;
import com.zaymax.dongdian.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 登陆验证服务
 * Created by huiquan on 2017/3/22.
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Autowired //用户
    private SysUserRepository userRepository;

    @Autowired //权限
    private SysAuthorityRepository authorityRepository;

    @Value(value = "${application.admin:admin}")
    private String admin;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("根据用户名[{}]查找用户", username);
        if (!Strings.isNullOrEmpty(username)) {
            Optional<SysUser> userOptional = userRepository.findTopByUsernameAndDeletedFalse(username);
            if (userOptional.isPresent()) {
                //获取用户权限
                List<GrantedAuthority> authorities = Lists.newArrayList();
                if (username.equals(admin)) {
                    LOGGER.debug("用户[{}]是超级管理员无视角色获取全部权限", username);
                    //超级管理员无视角色获取全部权限
                    authorities.addAll(authorityRepository.findByDeletedFalse());
                } else {
                    LOGGER.debug("根据用户ID[{}]获取权限", userOptional.get().getId());
                    authorities.addAll(authorityRepository.findDistinctSysAuthorityByDeletedFalseAndRoleAuthoritiesRoleUserRolesUserId(userOptional.get().getId()));
                }
                return new SecurityUser(userOptional.get(), authorities);
            }
            throw new UsernameNotFoundException(username);
        }
        return null;
    }
}
