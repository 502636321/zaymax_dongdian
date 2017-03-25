package com.zaymax.dongdian.service;

import com.zaymax.dongdian.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huiquan on 2017/3/2.
 */
public interface SystemService {

    //------------角色-开始------------//
    @Transactional(readOnly = true)
    Page<SysRole> findRolePage(Pageable pageable, SysRole role);

    @Transactional
    int deleteRole(String id);

    @Transactional(readOnly = true)
    SysRole findRole(String id);

    @Transactional
    SysRole editRole(String id, SysRole role);

    @Transactional
    SysRole saveRole(SysRole role);

    @Transactional(readOnly = true)
    boolean existRole(String name);

    /**
     * 返回所有的可用角色
     * @return
     */
    @Transactional(readOnly = true)
    List<SysRole> findAllRole();
    //------------角色-结束------------//

    List<LinkUserRole> findUserRole(String userId);

    //------------用户-开始------------//
    @Transactional(readOnly = true)
    Page<SysUser> findUserPage(Pageable pageable, SysUser user);

    @Transactional
    int deleteUser(String id);

    @Transactional(readOnly = true)
    SysUser findUser(String id);

    @Transactional
    SysUser editUser(String id, SysUser user);

    @Transactional
    SysUser saveUser(SysUser user);

    @Transactional(readOnly = true)
    boolean existUser(String username);

    @Transactional
    List<LinkUserRole> assignUser(String id, String[] to);

    /**
     * 重置指定用户的密码
     * @param id
     * @param user
     * @return
     */
    @Transactional
    SysUser newpasswordUser(String id, SysUser user);

    /**
     * 根据用户ID锁定或者解锁用户
     * @param id
     * @return
     */
    @Transactional
    int lockedUser(String id);
    //------------用户-结束------------//

    //------------系统配置-开始------------//
    SysProperties findTopProperties();

    SysProperties saveProperties(SysProperties properties);
    //------------系统配置-结束------------//

    //------------权限-开始------------//
    @Transactional(readOnly = true)
    Page<SysAuthority> findAuthorityPage(Pageable pageable, SysAuthority authority);

    @Transactional
    int deleteAuthority(String id);

    @Transactional(readOnly = true)
    SysAuthority findAuthority(String id);

    @Transactional
    SysAuthority editAuthority(String id, SysAuthority authority);

    @Transactional
    SysAuthority saveAuthority(SysAuthority authority);

    @Transactional(readOnly = true)
    List<SysAuthority> findAuthorities(String id);

    /**
     * 查询所有未删除的权限
     * @return
     */
    List<SysAuthority> findAllAuthority();
    //------------权限-结束------------//

    @Transactional(readOnly = true)
    List<SysProperties> findHistoryProperties();

    /**
     *  根据系统参数主键查询
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    SysProperties findProperties(String id);

    /**
     * 根据角色ID查询所属权限
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    List<LinkRoleAuthority> findRoleAuthorities(String id);

    /**
     * 根据角色ID指定跟权限集合指定
     * @param id
     * @param to
     * @return
     */
    @Transactional
    List<LinkRoleAuthority> authorityRole(String id, String[] to);

    //------------权限-结束------------//
}
