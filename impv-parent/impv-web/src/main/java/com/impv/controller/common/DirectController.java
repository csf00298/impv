package com.impv.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: Created by Admin on 2017/3/8.
 */
@Controller
public class DirectController extends BaseController {


    @RequestMapping("main/{services}")
    public ModelAndView toPage(@PathVariable("services") String page) {
            return view("main/" + page);
    }
}
