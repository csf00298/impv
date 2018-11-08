package com.impv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: SpringBoot 简单创建
 * 执行器  @SpringBootApplication注解 默认集成了
 * @Configuration、@EnableAutoConfiguration、
 * @EnableWebMvc、@ComponentScan 四个注解的功能
 */

@SpringBootApplication
public class StartBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartBoot.class, args);
    }
}
