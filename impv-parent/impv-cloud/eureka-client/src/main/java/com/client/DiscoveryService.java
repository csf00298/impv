package com.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: 服务发现
 * @Author: CaoXiaoLong create on 2017/6/20 15:20.
 */
//@RestController
//@Component
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/discovery")
    public String doDicoveryService() {
        StringBuilder sb = new StringBuilder();

        List<String> serviceIds = discoveryClient.getServices();
        if (!CollectionUtils.isEmpty(serviceIds)) {
            for (String serviceId : serviceIds) {
                System.out.println("serviceId:" + serviceId);
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
                if(!CollectionUtils.isEmpty(serviceInstances)){
                    for (ServiceInstance si : serviceInstances) {
                        sb.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
                    }
                }else{
                    sb.append("no service.");
                }
            }
        }
        return sb.toString();
    }


}
