package com.impv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: @RestController 等价于 @Controller + @ResponseBody
 * @Author: CaoXiaoLong create on 2017/5/19 18:20.
 */
@RestController
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String helloWorld(){
        logger.info("request to Json");
        return "helloWorld11111";
    }
}
