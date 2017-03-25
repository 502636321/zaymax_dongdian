package com.zaymax.dongdian.controller;

import com.zaymax.dongdian.domain.*;
import com.zaymax.dongdian.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huiquan on 2017/3/2.
 */
@Controller
@RequestMapping(value = {"admin/system"})
public class SystemController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    /**
     * 系统管理主界面
     * @return
     */
//    @Secured(value = SysAuthority.ROLE_SystemController$indexBasic)
    @RequestMapping(value = {""})
    public String indexBasic() {
        LOGGER.debug("进入系统管理");
        return "admin/system/system_index";
    }

    /*--------------角色--------------*/

    /**
     * 角色主页
     * @param page
     * @param size
     * @param sort
     * @param role
     * @param model
     * @return
     */
    @RequestMapping(value = {"role/index", "role"})
    public String indexRole(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "role") SysRole role,
            Model model
    ) {
        LOGGER.debug("进入角色主页");
        Page<SysRole> rolePage = systemService.findRolePage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), role);
        model.addAttribute("rolePage", rolePage);
        return "admin/system/role/role_index";
    }

    /**
     * 角色增加跳入
     * @return
     */
    @GetMapping(value = {"role/save"})
    public String saveRole() {
        LOGGER.debug("角色增加跳入");
        return "admin/system/role/role_save";
    }

    /**
     * 角色增加操作
     * @param role
     * @param model
     * @return
     */
    @PostMapping(value = {"role/save"})
    public String saveRole(
            @ModelAttribute(name = "role") SysRole role,
            Model model
    ) {
        LOGGER.debug("角色增加操作");
        SysRole saveRole = systemService.saveRole(role);
        if (saveRole != null) {
            setSuccessMessage(model, "role_action_save_success");
        } else {
            setSuccessMessage(model, "role_action_save_error");
        }
        return "admin/system/role/role_save";
    }

    /**
     * 角色编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"role/edit/{id}"})
    public String editRole(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑角色", id);
        SysRole role = systemService.findRole(id);
        model.addAttribute("role", role);
        return "admin/system/role/role_edit";
    }

    @PostMapping(value = {"role/edit/{id}"})
    public String editRole(
            @PathVariable String id,
            @ModelAttribute(name = "role") SysRole role,
            Model model
    ) {
        LOGGER.debug("编辑角色操作");
        SysRole editRole = systemService.editRole(id, role);
        if (editRole != null) {
            setSuccessMessage(model, "role_action_edit_success");
            role = editRole;
        } else {
            setErrorMessage(model, "role_action_edit_error");
        }
        return "admin/system/role/role_edit";
    }

    @RequestMapping(value = {"role/delete/{id}"})
    public String deleteRole(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "role") SysRole role,
            Model model
    ) {
        LOGGER.debug("根据ID删除角色", id);
        int count = systemService.deleteRole(id);
        SysRole deleteRole = systemService.findRole(id);
        if (count > 0) {
            setSuccessMessage(model, "role_action_delete_success", new Object[]{deleteRole.getName()});
        } else {
            setErrorMessage(model, "role_action_delete_error", new Object[]{deleteRole.getName()});
        }
        return indexRole(page, size, sort, role, model);
    }

    @RequestMapping(value = {"role/show/{id}"})
    public String showRole(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID显示角色", id);
        SysRole role = systemService.findRole(id);
        model.addAttribute("role", role);
        return "admin/system/role/role_show";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"role/authority/{id}"})
    public String authorityRole(
            @PathVariable String id,
            Model model
    ) {
        SysRole role = systemService.findRole(id);
        model.addAttribute("role", role);
        model.addAttribute("authorities", systemService.findAllAuthority());
        model.addAttribute("roleAuthorities", systemService.findRoleAuthorities(id));
        return "admin/system/role/role_authority";
    }

    @PostMapping(value = {"role/authority/{id}"})
    public String authorityRole(
            @PathVariable String id,
            @RequestParam(required = false) String[] to,
            @RequestParam(required = false) String[] from,
            Model model
    ) {
        SysRole role = systemService.findRole(id);
        List<LinkRoleAuthority> roleAuthorities = systemService.authorityRole(id, to);
        if (roleAuthorities != null) {
            setSuccessMessage(model, "role_action_authority_success", new Object[]{role.getName(), roleAuthorities.size()});
        } else {
            setErrorMessage(model, "role_action_authority_error");
        }
        model.addAttribute("role", role);
        model.addAttribute("authorities", systemService.findAllAuthority());
        model.addAttribute("roleAuthorities", systemService.findRoleAuthorities(id));
        return "admin/system/role/role_authority";
    }

    @RequestMapping(value = {"role/exist"})
    @ResponseBody
    public boolean existRole(
            @RequestParam(name = "name") String name
    ) {
        return systemService.existRole(name);
//        return false;
    }
    
    /*--------------用户--------------*/

    /**
     * 用户主页
     * @param page
     * @param size
     * @param sort
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = {"user/index", "user"})
    public String indexUser(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "user") SysUser user,
            Model model
    ) {
        LOGGER.debug("进入用户主页");
        Page<SysUser> userPage = systemService.findUserPage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), user);
        model.addAttribute("userPage", userPage);
        return "admin/system/user/user_index";
    }

    /**
     * 用户增加跳入
     * @return
     */
    @GetMapping(value = {"user/save"})
    public String saveUser() {
        LOGGER.debug("用户增加跳入");
        return "admin/system/user/user_save";
    }

    /**
     * 用户增加操作
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = {"user/save"})
    public String saveUser(
            @ModelAttribute(name = "user") SysUser user,
            Model model
    ) {
        LOGGER.debug("用户增加操作");
        SysUser saveUser = systemService.saveUser(user);
        if (saveUser != null) {
            setSuccessMessage(model, "user_action_save_success");
        } else {
            setSuccessMessage(model, "user_action_save_error");
        }
        return "admin/system/user/user_save";
    }

    /**
     * 用户编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"user/edit/{id}"})
    public String editUser(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑用户", id);
        SysUser user = systemService.findUser(id);
        model.addAttribute("user", user);
        return "admin/system/user/user_edit";
    }

    @PostMapping(value = {"user/edit/{id}"})
    public String editUser(
            @PathVariable String id,
            @ModelAttribute(name = "user") SysUser user,
            Model model
    ) {
        LOGGER.debug("编辑用户操作");
        SysUser editUser = systemService.editUser(id, user);
        if (editUser != null) {
            setSuccessMessage(model, "user_action_edit_success");
            user = editUser;
        } else {
            setErrorMessage(model, "user_action_edit_error");
        }
        return "admin/system/user/user_edit";
    }

    @RequestMapping(value = {"user/delete/{id}"})
    public String deleteUser(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "user") SysUser user,
            Model model
    ) {
        LOGGER.debug("根据ID删除用户", id);
        int count = systemService.deleteUser(id);
        SysUser deleteUser = systemService.findUser(id);
        if (count > 0) {
            setSuccessMessage(model, "user_action_delete_success", new Object[]{deleteUser.getUsername()});
        } else {
            setErrorMessage(model, "user_action_delete_error", new Object[]{deleteUser.getUsername()});
        }
        return indexUser(page, size, sort, user, model);
    }

    @RequestMapping(value = {"user/show/{id}"})
    public String showUser(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]显示用户", id);
        SysUser user = systemService.findUser(id);
        model.addAttribute("user", user);
        return "admin/system/user/user_show";
    }

    /**
     * 进入角色分配界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"user/assign/{id}"})
    public String assignUser(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]分配用户角色", id);
        model.addAttribute("user", systemService.findUser(id));
        model.addAttribute("roles", systemService.findAllRole());
        model.addAttribute("userRoles", systemService.findUserRole(id));
        return "admin/system/user/user_assign";
    }

    @PostMapping(value = {"user/assign/{id}"})
    public String assignUser(
            @PathVariable String id,
            @RequestParam(required = false) String[] to,
            @RequestParam(required = false) String[] from,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]分配用户角色", id);
        SysUser user = systemService.findUser(id);
        List<LinkUserRole> linkUserRoles = systemService.assignUser(id, to);
        if (linkUserRoles != null) {
            setSuccessMessage(model, "user_action_assign_success", new Object[]{user.getUsername(), linkUserRoles.size()});
        } else {
            setErrorMessage(model, "user_action_assign_error", new Object[]{user.getUsername()});
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", systemService.findAllRole());
        model.addAttribute("userRoles", systemService.findUserRole(id));
        return "admin/system/user/user_assign";
    }

    /**
     * 进入重置密码的界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"user/newpassword/{id}"})
    public String newpasswordUser(
        @PathVariable String id,
        Model model
    ) {
        LOGGER.debug("进入用户ID[{}]的重置密码界面", id);
        model.addAttribute("user", systemService.findUser(id));
        return "admin/system/user/user_newpassword";
    }

    @PostMapping(value = {"user/newpassword/{id}"})
    public String newpasswordUser(
            @PathVariable String id,
            @ModelAttribute(value = "user") SysUser user,
            Model model
    ) {
        LOGGER.debug("开始用户ID[{}]的重置密码操作", id);
        SysUser oldUser = systemService.findUser(id);
        SysUser newpasswordUser = systemService.newpasswordUser(id, user);
        if (newpasswordUser != null) {
            setSuccessMessage(model, "user_action_newpassword_success", new Object[]{oldUser.getUsername()});
        } else {
            setErrorMessage(model, "user_action_newpassword_error", new Object[]{oldUser.getUsername()});
        }
        return "admin/system/user/user_newpassword";
    }

    /**
     * 锁定或者解锁指定的用户
     * @param page
     * @param size
     * @param sort
     * @param user
     * @param model
     * @return
     */
    @GetMapping(value = {"user/locked/{id}"})
    public String lockedUser(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "user") SysUser user,
            Model model
    ) {
        int i = systemService.lockedUser(id);
        SysUser lockedUser = systemService.findUser(id);
        if (i > 0) {
            setSuccessMessage(model, Boolean.TRUE.equals(lockedUser.getLocked()) ? "user_action_locked_success" : "user_action_unlocked_success", new Object[]{lockedUser.getUsername()});
        } else {
            setErrorMessage(model, Boolean.TRUE.equals(lockedUser.getLocked()) ? "user_action_locked_error" : "user_action_unlocked_error", new Object[]{lockedUser.getUsername()});
        }
        return indexUser(page, size, sort, user, model);
    }

    @RequestMapping(value = {"user/exist"})
    @ResponseBody
    public boolean existUser(
            @RequestParam(name = "username") String username
    ) {
        return systemService.existUser(username);
    }
    
    /*--------------权限--------------*/

    /**
     * 权限主页
     * @param page
     * @param size
     * @param sort
     * @param authority
     * @param model
     * @return
     */
    @RequestMapping(value = {"authority/index", "authority"})
    public String indexAuthority(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "authority") SysAuthority authority,
            Model model
    ) {
        LOGGER.debug("进入权限主页");
        Page<SysAuthority> authorityPage = systemService.findAuthorityPage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), authority);
        model.addAttribute("authorityPage", authorityPage);
        return "admin/system/authority/authority_index";
    }

    /**
     * 权限增加跳入
     * @return
     */
    @GetMapping(value = {"authority/save"})
    public String saveAuthority() {
        LOGGER.debug("权限增加跳入");
        return "admin/system/authority/authority_save";
    }

    /**
     * 权限增加操作
     * @param authority
     * @param model
     * @return
     */
    @PostMapping(value = {"authority/save"})
    public String saveAuthority(
            @ModelAttribute(name = "authority") SysAuthority authority,
            Model model
    ) {
        LOGGER.debug("权限增加操作");
        SysAuthority saveAuthority = systemService.saveAuthority(authority);
        if (saveAuthority != null) {
            setSuccessMessage(model, "authority_action_save_success");
        } else {
            setSuccessMessage(model, "authority_action_save_error");
        }
        return "admin/system/authority/authority_save";
    }

    /**
     * 权限编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"authority/edit/{id}"})
    public String editAuthority(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑权限", id);
        SysAuthority authority = systemService.findAuthority(id);
        model.addAttribute("authority", authority);
        return "admin/system/authority/authority_edit";
    }

    @PostMapping(value = {"authority/edit/{id}"})
    public String editAuthority(
            @PathVariable String id,
            @ModelAttribute(name = "authority") SysAuthority authority,
            Model model
    ) {
        LOGGER.debug("编辑权限操作");
        SysAuthority editAuthority = systemService.editAuthority(id, authority);
        if (editAuthority != null) {
            setSuccessMessage(model, "authority_action_edit_success");
            authority = editAuthority;
        } else {
            setErrorMessage(model, "authority_action_edit_error");
        }
        return "admin/system/authority/authority_edit";
    }

    @RequestMapping(value = {"authority/delete/{id}"})
    public String deleteAuthority(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "authority") SysAuthority authority,
            Model model
    ) {
        LOGGER.debug("根据ID删除权限", id);
        int count = systemService.deleteAuthority(id);
        SysAuthority deleteAuthority = systemService.findAuthority(id);
        if (count > 0) {
            setSuccessMessage(model, "authority_action_delete_success", new Object[]{deleteAuthority.getName()});
        } else {
            setErrorMessage(model, "authority_action_delete_error", new Object[]{deleteAuthority.getName()});
        }
        return indexAuthority(page, size, sort, authority, model);
    }

    @RequestMapping(value = {"authority/show/{id}"})
    public String showAuthority(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID显示权限", id);
        SysAuthority authority = systemService.findAuthority(id);
        model.addAttribute("authority", authority);
        return "admin/system/authority/authority_show";
    }
}
