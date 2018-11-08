package com.demo.tspring.EEBook4.controller;

import com.demo.tspring.EEBook4.pojo.TestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: CaoXiaoLong create on 2017/7/20 18:01.
 */
@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomeString(@ModelAttribute("msg") String msg, TestEntity entity){
        System.out.println(entity.toString());
        throw new IllegalArgumentException("throw Exception "+msg);
    }
}
