package com.impv.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class Test1Service {

    @Autowired
    private Test2Service test2Service;

//    @PostConstruct
//    public void install(){
//        System.out.println("install1");
//        test2Service.doSomeThing2();
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.decrementAndGet();
//    }

    @RequestMapping("/doSomeThing1")
    public void doSomThing1(){
        test2Service.doSomeThing2();
        System.out.println("doSomThing1");
    }
}
