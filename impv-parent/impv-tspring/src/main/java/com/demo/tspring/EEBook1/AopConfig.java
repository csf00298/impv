package com.demo.tspring.EEBook1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description: 配置类
 * @Author: CaoXiaoLong create on 2017/7/19 17:12.
 */

@Configuration
@ComponentScan("com.demo.tspring.EEBook1")
@EnableAspectJAutoProxy //开启Spring对AspectJ代理的支持
public class AopConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);

        demoAnnotationService.add();
        demoMethodService.add();
    }
}
