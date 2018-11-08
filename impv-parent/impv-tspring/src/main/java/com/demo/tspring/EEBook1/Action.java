package com.demo.tspring.EEBook1;

import java.lang.annotation.*;

/**
 * @Description: 注解规则
 * @Author: CaoXiaoLong create on 2017/7/19 11:13.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
