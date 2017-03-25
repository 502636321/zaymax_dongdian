package com.zaymax.dongdian.service.imp;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.*;
import com.zaymax.dongdian.repository.*;
import com.zaymax.dongdian.security.PasswordEncoder;
import com.zaymax.dongdian.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by huiquan on 2017/3/2.
 */
@Service
public class SystemServiceImp implements SystemService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SystemServiceImp.class);

    @Autowired //角色
    private SysRoleRepository roleRepository;
    
    @Autowired //用户
    private SysUserRepository userRepository;

    @Autowired //系统配置
    private SysPropertiesRepository propertiesRepository;

    @Autowired //权限
    private SysAuthorityRepository authorityRepository;

    @Autowired //密码加密器
    private PasswordEncoder passwordEncoder;

    @Autowired //用户角色关联
    private LinkUserRoleRepository userRoleRepository;

    @Autowired //角色权限关联
    private LinkRoleAuthorityRepository roleAuthorityRepository;

    //------------角色-开始------------//
    @Override
    public Page<SysRole> findRolePage(Pageable pageable, SysRole role) {
        return roleRepository.findAll(new Specification<SysRole>() {
            @Override
            public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
                return null;
            }
        }, pageable);
    }

    @Override
    public int deleteRole(String id) {
        return roleRepository.delete(Lists.newArrayList(id));
    }

    @Override
    public SysRole findRole(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return roleRepository.findOne(id);
        }
        return null;
    }

    @Override
    public SysRole editRole(String id, SysRole role) {
        if (!Strings.isNullOrEmpty(id) && role != null) {
            SysRole editRole = roleRepository.findOne(id);
            if (editRole != null) {
                editRole.setName(role.getName());
                editRole.setDescription(role.getDescription());
                return roleRepository.save(role);
            }
        }
        return null;
    }

    @Override
    public SysRole saveRole(SysRole role) {
        if (role != null) {
            return roleRepository.save(role);
        }
        return null;
    }

    @Override
    public boolean existRole(String name) {
        return !roleRepository.findTopByNameAndDeletedFalse(name).isPresent();
    }

    @Override
    public List<SysRole> findAllRole() {
        return roleRepository.findByDeletedFalse();
    }

    @Override
    public List<LinkUserRole> findUserRole(String userId) {
        return userRoleRepository.findByDeletedFalseAndUserId(userId);
    }
    //------------角色-结束------------//

    //------------用户-开始------------//
    @Override
    public Page<SysUser> findUserPage(Pageable pageable, SysUser user) {
        return userRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
                return null;
            }
        }, pageable);
    }

    @Override
    public int deleteUser(String id) {
        return userRepository.delete(Lists.newArrayList(id));
    }

    @Override
    public SysUser findUser(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return userRepository.findOne(id);
        }
        return null;
    }

    @Override
    public SysUser editUser(String id, SysUser user) {
        if (!Strings.isNullOrEmpty(id) && user != null) {
            SysUser editUser = userRepository.findOne(id);
            if (editUser != null) {
                editUser.setName(user.getName());
                editUser.setDescription(user.getDescription());
                return userRepository.save(editUser);
            }
        }
        return null;
    }

    @Override
    public SysUser saveUser(SysUser user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean existUser(String username) {
        return !userRepository.findTopByUsernameAndDeletedFalse(username).isPresent();
    }

    @Override
    public List<LinkUserRole> assignUser(String id, String[] to) {
        try {
            SysUser user = null;
            List<LinkUserRole> linkUserRoles = Lists.newArrayList();
            if (!Strings.isNullOrEmpty(id)
                    && (user = userRepository.findOne(id)) != null) {
                //先删除旧角色记录
                userRoleRepository.deleteByUserId(Lists.newArrayList(id));
                //新增新角色
                List<String> strings = Lists.newArrayList(to == null ? new String[]{} : to);
                strings.add(UUID.randomUUID().toString());
                List<SysRole> roles = roleRepository.findByIdIn(strings);
                if (!CollectionUtils.isEmpty(roles)) {
                    for (SysRole role : roles) {
                        linkUserRoles.add(userRoleRepository.save(new LinkUserRole(user, role)));
                    }
                }
                return linkUserRoles;
            }
            return linkUserRoles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SysUser newpasswordUser(String id, SysUser user) {
        if (!Strings.isNullOrEmpty(id)
                && user != null
                && !Strings.isNullOrEmpty(user.getPassword())) {
            try {
                SysUser newpasswordUser = userRepository.findOne(id);
                newpasswordUser.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(newpasswordUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int lockedUser(String id) {
        return userRepository.lockedOrUnlockedUser(id);
    }
    //------------用户-结束------------//

    //------------系统配置-开始------------//
    @Override
    public SysProperties findTopProperties() {
        //系统配置，如果已存在则返回最新的，没有就新建一个
        Optional<SysProperties> propertiesOptional = propertiesRepository.findTopByDeletedFalseOrderByLastModifiedDateDesc();
        if (propertiesOptional.isPresent()) {
            return propertiesOptional.get();
        } else {
            return propertiesRepository.save(new SysProperties());
        }
    }

    @Override
    public SysProperties saveProperties(SysProperties properties) {
        //每次操作都增加新纪录
        if (properties != null) {
            return propertiesRepository.save(properties);
        }
        return null;
    }
    //------------系统配置-结束------------//

    //------------权限-开始------------//
    @Override
    public Page<SysAuthority> findAuthorityPage(Pageable pageable, SysAuthority authority) {
        return authorityRepository.findAll(new Specification<SysAuthority>() {
            @Override
            public Predicate toPredicate(Root<SysAuthority> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
                return null;
            }
        }, pageable);
    }

    @Override
    public int deleteAuthority(String id) {
        return authorityRepository.delete(Lists.newArrayList(id));
    }

    @Override
    public SysAuthority findAuthority(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return authorityRepository.findOne(id);
        }
        return null;
    }

    @Override
    public SysAuthority editAuthority(String id, SysAuthority authority) {
        if (!Strings.isNullOrEmpty(id) && authority != null) {
            SysAuthority editAuthority = authorityRepository.findOne(id);
            if (editAuthority != null) {
                editAuthority.setName(authority.getName());
                editAuthority.setDescription(authority.getDescription());
                return authorityRepository.save(authority);
            }
        }
        return null;
    }

    @Override
    public SysAuthority saveAuthority(SysAuthority authority) {
        if (authority != null) {
            return authorityRepository.save(authority);
        }
        return null;
    }

    @Override
    public List<SysAuthority> findAuthorities(String id) {
        return authorityRepository.findByRoleAuthoritiesRoleId(id);
    }

    @Override
    public List<SysAuthority> findAllAuthority() {
        return authorityRepository.findByDeletedFalse();
    }

    @Override
    public List<SysProperties> findHistoryProperties() {
        return propertiesRepository.findTop20ByDeletedFalseOrderByCreatedDateDesc();
    }

    @Override
    public SysProperties findProperties(String id) {
        LOGGER.debug("根据ID[{}]查询系统参数", id);
        return propertiesRepository.findOne(id);
    }

    @Override
    public List<LinkRoleAuthority> findRoleAuthorities(String id) {
        return roleAuthorityRepository.findByDeletedFalseAndRoleId(id);
    }

    @Override
    public List<LinkRoleAuthority> authorityRole(String id, String[] to) {
        LOGGER.debug("根据角色ID[{}]指定权限集合", id);
        try {
            List<LinkRoleAuthority> roleAuthorities = Lists.newArrayList();
            SysRole role = null;
            if (!Strings.isNullOrEmpty(id)
                    && (role = roleRepository.findOne(id)) != null) {
                //删除旧记录
                roleAuthorityRepository.deleteByRoleId(Lists.newArrayList(id));
                //新增角色权限
                List<String> strings = Lists.newArrayList(to == null ? new String[]{} : to);
                strings.add(UUID.randomUUID().toString());
                List<SysAuthority> authorities = authorityRepository.findByIdIn(strings);
                for (SysAuthority authority : authorities) {
                    roleAuthorities.add(roleAuthorityRepository.save(new LinkRoleAuthority(role, authority)));
                };
            }
            return roleAuthorities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //------------权限-结束------------//
}
