package com.zaymax.dongdian.controller;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.SysAuthority;
import com.zaymax.dongdian.service.BasicService;
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

/**
 * Created by huiquan on 2017/3/2.
 */
@Controller
@RequestMapping(value = {"admin/basic"})
public class BasicController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BasicController.class);

    @Autowired
    private BasicService basicService;

    @Secured(value = SysAuthority.ROLE_BasicController$indexBasic)
    @RequestMapping(value = {""})
    public String indexBasic() {
        LOGGER.debug("进入基础配置");
        return "admin/basic/basic_index";
    }

    /*--------------学历--------------*/

    /**
     * 学历主页
     * @param page
     * @param size
     * @param sort
     * @param education
     * @param model
     * @return
     */
    @RequestMapping(value = {"education/index", "education"})
    public String indexCountry(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "education") BaseCountry education,
            Model model
    ) {
        LOGGER.debug("进入学历主页");
        Page<BaseCountry> educationPage = basicService.findCountryPage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), education);
        model.addAttribute("educationPage", educationPage);
        return "admin/basic/education/education_index";
    }

    /**
     * 角色增加跳入
     * @return
     */
    @GetMapping(value = {"education/save"})
    public String saveCountry() {
        LOGGER.debug("学历增加跳入");
        return "admin/basic/education/education_save";
    }

    /**
     * 角色增加操作
     * @param education
     * @param model
     * @return
     */
    @PostMapping(value = {"education/save"})
    public String saveCountry(
            @ModelAttribute(name = "education") BaseCountry education,
            Model model
    ) {
        LOGGER.debug("学历增加操作");
        BaseCountry saveCountry = basicService.saveCountry(education);
        if (saveCountry != null) {
            setSuccessMessage(model, "education_action_save_success");
        } else {
            setSuccessMessage(model, "education_action_save_error");
        }
        return "admin/basic/education/education_save";
    }

    /**
     * 角色编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"education/edit/{id}"})
    public String editCountry(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑学历", id);
        BaseCountry education = basicService.findCountry(id);
        model.addAttribute("education", education);
        return "admin/basic/education/education_edit";
    }

    @PostMapping(value = {"education/edit/{id}"})
    public String editCountry(
            @PathVariable String id,
            @ModelAttribute(name = "education") BaseCountry education,
            Model model
    ) {
        LOGGER.debug("编辑学历操作");
        BaseCountry editCountry = basicService.editCountry(id, education);
        if (editCountry != null) {
            setSuccessMessage(model, "education_action_edit_success");
            education = editCountry;
        } else {
            setErrorMessage(model, "education_action_edit_error");
        }
        return "admin/basic/education/education_edit";
    }

    @RequestMapping(value = {"education/delete/{id}"})
    public String deleteCountry(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "education") BaseCountry education,
            Model model
    ) {
        LOGGER.debug("根据ID删除学历", id);
        int count = basicService.deleteCountry(id);
        BaseCountry deleteCountry = basicService.findCountry(id);
        if (count > 0) {
            setSuccessMessage(model, "education_action_delete_success", new Object[]{deleteCountry.getName()});
        } else {
            setErrorMessage(model, "education_action_delete_error", new Object[]{deleteCountry.getName()});
        }
        return indexCountry(page, size, sort, education, model);
    }

    @RequestMapping(value = {"education/show/{id}"})
    public String showCountry(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID显示学历", id);
        BaseCountry education = basicService.findCountry(id);
        model.addAttribute("education", education);
        return "admin/basic/education/education_show";
    }

     
}
