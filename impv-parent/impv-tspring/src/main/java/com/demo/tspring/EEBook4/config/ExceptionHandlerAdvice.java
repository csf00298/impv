package com.demo.tspring.EEBook4.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 异常处理
 * @Author: CaoXiaoLong create on 2017/7/20 17:50.
 */

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value=Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","ERROR Msg");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id"); //设置不允许通过的字段 忽略request参数的id
    }
}
