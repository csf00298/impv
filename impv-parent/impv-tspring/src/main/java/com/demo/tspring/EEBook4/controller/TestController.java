package com.demo.tspring.EEBook4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: CaoXiaoLong create on 2017/7/20 15:40.
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }
}
