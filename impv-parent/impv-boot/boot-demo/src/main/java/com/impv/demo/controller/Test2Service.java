package com.impv.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class Test2Service {

    @Autowired
    private Test1Service test1Service;

//    @PostConstruct
//    public void install(){
//        System.out.println("install2");
//        test1Service.doSomThing1();
//    }

    @RequestMapping("/doSomeThing2")
    public void doSomeThing2(){
        test1Service.doSomThing1();
        System.out.println("doSomeThing2");
    }
}
