package com.zaymax.dongdian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用控制器
 * Created by soy50 on 2017/3/22.
 */
@Controller
public class CommonController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = {"login"})
    public String login() {
        return "login";
    }

    public String index() {
        return "index";
    }

}
