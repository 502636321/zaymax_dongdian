package com.zaymax.dongdian.controller;

import com.google.common.base.Strings;
import com.zaymax.dongdian.domain.SysAuthority;
import com.zaymax.dongdian.domain.SysProperties;
import com.zaymax.dongdian.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huiquan on 2017/3/3.
 */
@Controller
@RequestMapping(value = {"admin/properties"})
public class PropertiesController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PropertiesController.class);

    @Autowired
    private SystemService systemService;

    @Secured(value = SysAuthority.ROLE_PropertiesController$indexProperties)
    @GetMapping(value = {"", "index"})
    public String indexProperties(
            Model model
    ) {
        LOGGER.debug("进入系统参数主界面");
        return "admin/system/properties/properties_index";
    }

    @GetMapping(value = {"save"})
    public String saveProperties(
            @RequestParam(required = false) String id,
            Model model
    ) {
        LOGGER.debug("进入系统参数设置界面");
        SysProperties properties = null;
        if (!Strings.isNullOrEmpty(id)) {
            properties = systemService.findProperties(id);
        } else {
            properties = systemService.findTopProperties();
        }
        model.addAttribute("properties", properties);
        model.addAttribute("historyProperties", systemService.findHistoryProperties());
        return "admin/system/properties/properties_save";
    }

    @PostMapping(value = {"save"})
    public String saveProperties(
            @ModelAttribute(value = "properties") SysProperties properties,
            @RequestParam(required = false, defaultValue = "false") boolean restore,
            Model model
    ) {
        LOGGER.debug("系统参数更新操作[{}]", properties);
        SysProperties newProperties = systemService.saveProperties(properties);
        if (newProperties != null) {
            setSuccessMessage(model, restore ? "properties_action_restore_success" : "properties_action_save_success");
        } else {
            setErrorMessage(model, restore ? "properties_action_restore_error" : "properties_action_save_error");
        }
        model.addAttribute("historyProperties", systemService.findHistoryProperties());
        model.addAttribute("lastProperties", systemService.findTopProperties());
        return "admin/system/properties/properties_save";
    }
}
