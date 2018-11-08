package com.impv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:  @Controller返回model页面
 * @Author: CaoXiaoLong create on 2017/5/22 14:16.
 */
@Controller
public class HelloWorldPageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/toHw/{name}")
    public String toPage(@PathVariable("name") String user, Model model){
        logger.info("request to Page");
        model.addAttribute("name",user);
        return "helloWorld";
    }
}
