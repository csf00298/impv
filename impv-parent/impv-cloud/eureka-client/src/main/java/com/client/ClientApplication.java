package com.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Description: EurekaClient
 * @Author: CaoXiaoLong create on 2017/6/20 14:38.
 */

@SpringBootApplication
//@EnableEurekaClient
//@RestController
//@EnableAutoConfiguration
public class ClientApplication {
//    @RequestMapping("/")
//    public String home() {
//        return "Hello world";
//    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).web(true).run(args);
    }
}
