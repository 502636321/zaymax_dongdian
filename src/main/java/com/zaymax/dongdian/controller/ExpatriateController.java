package com.zaymax.dongdian.controller;

import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.BaseEmployer;
import com.zaymax.dongdian.domain.SysExpatriate;
import com.zaymax.dongdian.domain.enums.CfgGender;
import com.zaymax.dongdian.domain.enums.CfgSettlementState;
import com.zaymax.dongdian.service.BasicService;
import com.zaymax.dongdian.service.ExpatriateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 外派人员控制器
 * Created by soy50 on 2017/3/26.
 */
@Controller
public class ExpatriateController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExpatriateController.class);

    @Autowired
    private ExpatriateService expatriateService;

    @Autowired
    private BasicService basicService;


    /**
     * 外派人员主页
     * @param page
     * @param size
     * @param sort
     * @param expatriate
     * @param model
     * @return
     */
    @RequestMapping(value = {"expatriate/index", "expatriate", "index", ""})
    public String indexExpatriate(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "expatriate") SysExpatriate expatriate,
            Model model
    ) {
        LOGGER.debug("进入外派人员主页");
        Page<SysExpatriate> expatriatePage = expatriateService.findExpatriatePage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), expatriate);
        model.addAttribute("expatriatePage", expatriatePage);
        return "admin/expatriate/expatriate_index";
    }

    @ResponseBody
    @RequestMapping(value = {"expatriate/index.json", "expatriate.json", "index.json"})
    public Page<SysExpatriate> indexExpatriateJson(
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "expatriate") SysExpatriate expatriate
    ) {
        return expatriateService.findExpatriatePage(new PageRequest(page, size, new Sort(Sort.Direction.DESC, sort)), expatriate);
    }

    /**
     * 进入外派人员增加界面
     * @return
     */
    @GetMapping(value = {"expatriate/save", "save"})
    public String saveExpatriate(
            Model model
    ) {
        LOGGER.debug("进入外派人员增加界面");
        model.addAttribute("genders", CfgGender.values());
        model.addAttribute("settlementes", CfgSettlementState.values());
        model.addAttribute("countries", basicService.findAllCountry());
        model.addAttribute("employers", basicService.findAllEmployer());
        return "admin/expatriate/expatriate_save";
    }

    /**
     * 外派人员增加操作
     * @param expatriate
     * @param model
     * @return
     */
    @PostMapping(value = {"expatriate/save", "save"})
    public String saveExpatriate(
            @ModelAttribute(name = "expatriate") SysExpatriate expatriate,
            Model model
    ) {
        LOGGER.debug("外派人员增加操作");
        SysExpatriate saveExpatriate = expatriateService.saveExpatriate(expatriate);
        if (saveExpatriate != null) {
            setSuccessMessage(model, "expatriate_action_save_success");
        } else {
            setSuccessMessage(model, "expatriate_action_save_error");
        }
        model.addAttribute("genders", CfgGender.values());
        model.addAttribute("settlementes", CfgSettlementState.values());
        model.addAttribute("countries", basicService.findAllCountry());
        model.addAttribute("employers", basicService.findAllEmployer());
        return "admin/expatriate/expatriate_save";
    }

    /**
     * 进入外派人员的编辑
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = {"expatriate/edit/{id}", "edit/{id}"})
    public String editExpatriate(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]编辑外派人员", id);
        SysExpatriate expatriate = expatriateService.findExpatriate(id);
        model.addAttribute("expatriate", expatriate);
        model.addAttribute("genders", CfgGender.values());
        model.addAttribute("settlementes", CfgSettlementState.values());
        model.addAttribute("countries", basicService.findAllCountry());
        model.addAttribute("employers", basicService.findAllEmployer());

        return "admin/expatriate/expatriate_edit";
    }

    /**
     * 外派人员的编剧操作
     * @param id
     * @param expatriate
     * @param model
     * @return
     */
    @PostMapping(value = {"expatriate/edit/{id}", "edit/{id}"})
    public String editExpatriate(
            @PathVariable String id,
            @ModelAttribute(name = "expatriate") SysExpatriate expatriate,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]外派人员的编剧操作", id);
        SysExpatriate editExpatriate = expatriateService.editExpatriate(id, expatriate);
        if (editExpatriate != null) {
            setSuccessMessage(model, "expatriate_action_edit_success");
            expatriate = editExpatriate;
        } else {
            setErrorMessage(model, "expatriate_action_edit_error");
        }
        model.addAttribute("genders", CfgGender.values());
        model.addAttribute("settlementes", CfgSettlementState.values());
        model.addAttribute("countries", basicService.findAllCountry());
        model.addAttribute("employers", basicService.findAllEmployer());
        return "admin/expatriate/expatriate_edit";
    }

    /**
     * 根据ID删除外派人员
     * @param id
     * @param page
     * @param size
     * @param sort
     * @param expatriate
     * @param model
     * @return
     */
    @RequestMapping(value = {"expatriate/delete/{id}", "delete/{id}"})
    public String deleteExpatriate(
            @PathVariable String id,
            @RequestParam(defaultValue = PAGE_REQUEST_PAGE, required = false) int page,
            @RequestParam(defaultValue = PAGE_REQUEST_SIZE, required = false) int size,
            @RequestParam(defaultValue = PAGE_REQUEST_SORT, required = false) String sort,
            @ModelAttribute(name = "expatriate") SysExpatriate expatriate,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]删除外派人员", id);
        int count = expatriateService.deleteExpatriate(id);
        SysExpatriate deleteExpatriate = expatriateService.findExpatriate(id);
        if (count > 0) {
            setSuccessMessage(model, "expatriate_action_delete_success", new Object[]{deleteExpatriate.getName()});
        } else {
            setErrorMessage(model, "expatriate_action_delete_error", new Object[]{deleteExpatriate.getName()});
        }
        return indexExpatriate(page, size, sort, expatriate, model);
    }

    /**
     * 根据ID显示外派人员
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = {"expatriate/show/{id}", "show/{id}"})
    public String showExpatriate(
            @PathVariable String id,
            Model model
    ) {
        LOGGER.debug("根据ID[{}]显示外派人员", id);
        SysExpatriate expatriate = expatriateService.findExpatriate(id);
        model.addAttribute("expatriate", expatriate);
        model.addAttribute("genders", CfgGender.values());
        model.addAttribute("settlementes", CfgSettlementState.values());
        model.addAttribute("countries", basicService.findAllCountry());
        model.addAttribute("employers", basicService.findAllEmployer());
        return "admin/expatriate/expatriate_show";
    }
}
