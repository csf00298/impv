package com.demo.tspring.EEBook1;

import org.springframework.stereotype.Service;

/**
 * @Description: 注解式拦截类
 * @Author: CaoXiaoLong create on 2017/7/19 11:34.
 */
@Service
public class DemoAnnotationService {

    @Action(name="注解式拦截Add操作")
    public void add(){}
}
