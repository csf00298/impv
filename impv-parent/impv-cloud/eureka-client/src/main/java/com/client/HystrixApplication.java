package com.client;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Description:
 * @Author: CaoXiaoLong create on 2017/6/21 11:24.
 */

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableCircuitBreaker
public class HystrixApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }
}
