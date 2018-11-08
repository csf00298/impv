package com.server2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: Eureka集群节点2
 * @Author: CaoXiaoLong create on 2017/6/20 15:14.
 */

@SpringBootApplication
@EnableEurekaServer
public class ServerApplication2 {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServerApplication2.class).web(true).run(args);
    }
}
