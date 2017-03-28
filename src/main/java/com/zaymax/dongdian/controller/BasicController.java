package com.zaymax.dongdian.controller;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.BaseEmployer;
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

import java.util.List;

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

    /*--------------派往国别-开始--------------*/

    /**
     * 派往国别主页
     * @param page
     * @param size
     * @param sort
     * @param country
     * @param model
     * @return
     */
    @RequestMapping(value = {"country/index", "country"})
    public String indexCountry(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "country") BaseCountry country,
            Model model
    ) {
        LOGGER.debug("进入派往国别主页");
        Page<BaseCountry> countryPage = basicService.findCountryPage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), country);
        model.addAttribute("countryPage", countryPage);
        return "admin/basic/country/country_index";
    }

    /**
     * 角色增加跳入
     * @return
     */
    @GetMapping(value = {"country/save"})
    public String saveCountry() {
        LOGGER.debug("派往国别增加跳入");
        return "admin/basic/country/country_save";
    }

    /**
     * 角色增加操作
     * @param country
     * @param model
     * @return
     */
    @PostMapping(value = {"country/save"})
    public String saveCountry(
            @ModelAttribute(name = "country") BaseCountry country,
            Model model
    ) {
        LOGGER.debug("派往国别增加操作");
        BaseCountry saveCountry = basicService.saveCountry(country);
        if (saveCountry != null) {
            setSuccessMessage(model, "country_action_save_success");
        } else {
            setSuccessMessage(model, "country_action_save_error");
        }
        return "admin/basic/country/country_save";
    }

    /**
     * 角色编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"country/edit/{id}"})
    public String editCountry(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑派往国别", id);
        BaseCountry country = basicService.findCountry(id);
        model.addAttribute("country", country);
        return "admin/basic/country/country_edit";
    }

    @PostMapping(value = {"country/edit/{id}"})
    public String editCountry(
            @PathVariable String id,
            @ModelAttribute(name = "country") BaseCountry country,
            Model model
    ) {
        LOGGER.debug("编辑派往国别操作");
        BaseCountry editCountry = basicService.editCountry(id, country);
        if (editCountry != null) {
            setSuccessMessage(model, "country_action_edit_success");
            country = editCountry;
        } else {
            setErrorMessage(model, "country_action_edit_error");
        }
        return "admin/basic/country/country_edit";
    }

    @RequestMapping(value = {"country/delete/{id}"})
    public String deleteCountry(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "country") BaseCountry country,
            Model model
    ) {
        LOGGER.debug("根据ID删除派往国别", id);
        int count = basicService.deleteCountry(id);
        BaseCountry deleteCountry = basicService.findCountry(id);
        if (count > 0) {
            setSuccessMessage(model, "country_action_delete_success", new Object[]{deleteCountry.getName()});
        } else {
            setErrorMessage(model, "country_action_delete_error", new Object[]{deleteCountry.getName()});
        }
        return indexCountry(page, size, sort, country, model);
    }

    @RequestMapping(value = {"country/show/{id}"})
    public String showCountry(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID显示派往国别", id);
        BaseCountry country = basicService.findCountry(id);
        model.addAttribute("country", country);
        return "admin/basic/country/country_show";
    }

    /*--------------派往国别-结束--------------*/
    /*--------------雇主-开始--------------*/
    /**
     * 雇主主页
     * @param page
     * @param size
     * @param sort
     * @param employer
     * @param model
     * @return
     */
    @RequestMapping(value = {"employer/index", "employer"})
    public String indexEmployer(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "employer") BaseEmployer employer,
            Model model
    ) {
        LOGGER.debug("进入雇主主页");
        Page<BaseEmployer> employerPage = basicService.findEmployerPage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), employer);
        model.addAttribute("employerPage", employerPage);
        return "admin/basic/employer/employer_index";
    }

    /**
     * 角色增加跳入
     * @return
     */
    @GetMapping(value = {"employer/save"})
    public String saveEmployer() {
        LOGGER.debug("雇主增加跳入");
        return "admin/basic/employer/employer_save";
    }

    /**
     * 角色增加操作
     * @param employer
     * @param model
     * @return
     */
    @PostMapping(value = {"employer/save"})
    public String saveEmployer(
            @ModelAttribute(name = "employer") BaseEmployer employer,
            Model model
    ) {
        LOGGER.debug("雇主增加操作");
        BaseEmployer saveEmployer = basicService.saveEmployer(employer);
        if (saveEmployer != null) {
            setSuccessMessage(model, "employer_action_save_success");
        } else {
            setSuccessMessage(model, "employer_action_save_error");
        }
        return "admin/basic/employer/employer_save";
    }

    /**
     * 角色编辑跳入
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"employer/edit/{id}"})
    public String editEmployer(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID编辑雇主", id);
        BaseEmployer employer = basicService.findEmployer(id);
        model.addAttribute("employer", employer);
        return "admin/basic/employer/employer_edit";
    }

    @PostMapping(value = {"employer/edit/{id}"})
    public String editEmployer(
            @PathVariable String id,
            @ModelAttribute(name = "employer") BaseEmployer employer,
            Model model
    ) {
        LOGGER.debug("编辑雇主操作");
        BaseEmployer editEmployer = basicService.editEmployer(id, employer);
        if (editEmployer != null) {
            setSuccessMessage(model, "employer_action_edit_success");
            employer = editEmployer;
        } else {
            setErrorMessage(model, "employer_action_edit_error");
        }
        return "admin/basic/employer/employer_edit";
    }

    @RequestMapping(value = {"employer/delete/{id}"})
    public String deleteEmployer(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "employer") BaseEmployer employer,
            Model model
    ) {
        LOGGER.debug("根据ID删除雇主", id);
        int count = basicService.deleteEmployer(id);
        BaseEmployer deleteEmployer = basicService.findEmployer(id);
        if (count > 0) {
            setSuccessMessage(model, "employer_action_delete_success", new Object[]{deleteEmployer.getName()});
        } else {
            setErrorMessage(model, "employer_action_delete_error", new Object[]{deleteEmployer.getName()});
        }
        return indexEmployer(page, size, sort, employer, model);
    }

    @RequestMapping(value = {"employer/show/{id}"})
    public String showEmployer(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID显示雇主", id);
        BaseEmployer employer = basicService.findEmployer(id);
        model.addAttribute("employer", employer);
        return "admin/basic/employer/employer_show";
    }
    /*--------------雇主-结束--------------*/
    /*--------------补全提示-开始--------------*/
    @ResponseBody
    @RequestMapping(value = {"auto_complete/country"})
    public List<BaseCountry> autoCompleteCountry(
            @RequestParam(value = "term", required = false) String term
    ) {
        return basicService.findTopNameByCountry(term);
    }
    /*--------------补全提示-结束--------------*/
}
