package com.zaymax.dongdian.controller;

import com.zaymax.dongdian.domain.SysProperties;
import com.zaymax.dongdian.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.awt.print.PrinterAbortException;
import java.io.Serializable;

/**
 * Created by huiquan on 2017/2/27.
 */
public class BaseController implements Serializable, MessageSourceAware {
    public static final String PAGE_REQUEST_PAGE = "0";
    public static final String PAGE_REQUEST_SIZE = "15";
    public static final String PAGE_REQUEST_SORT = "lastModifiedDate";
    public static final String SUCCESS_MESSAGE = "success_msg";
    public static final String ERROR_MESSAGE = "error_msg";

    private MessageSource messageSource;

    @Autowired
    private SystemService systemService;

    @ModelAttribute(value = "lastProperties")
    public SysProperties getProperties() {
        return getSystemService().findTopProperties();
    }

    public void setSuccessMessage(Model model, String s) {
        model.addAttribute(SUCCESS_MESSAGE, getMessageSource().getMessage(s, null, LocaleContextHolder.getLocale()));
    }

    public void setSuccessMessage(Model model, String s, Object[] objects) {
        model.addAttribute(SUCCESS_MESSAGE, getMessageSource().getMessage(s, objects, LocaleContextHolder.getLocale()));
    }

    public void setErrorMessage(Model model, String s) {
        model.addAttribute(ERROR_MESSAGE, getMessageSource().getMessage(s, null, LocaleContextHolder.getLocale()));
    }

    public void setErrorMessage(Model model, String s, Object[] objects) {
        model.addAttribute(ERROR_MESSAGE, getMessageSource().getMessage(s, objects, LocaleContextHolder.getLocale()));
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public SystemService getSystemService() {
        return systemService;
    }

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
