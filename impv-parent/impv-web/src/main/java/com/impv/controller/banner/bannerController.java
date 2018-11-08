package com.impv.controller.banner;

import com.impv.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:banner Created by Admin on 2017/3/9.
 */
@Controller
@RequestMapping("/banner")
public class bannerController  extends BaseController{

    @RequestMapping("/topage/{page}")
    public ModelAndView toPage(@PathVariable("page") String page){
        return view("/banner/"+page);
    }
}
