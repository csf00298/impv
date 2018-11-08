package com.demo.tspring.EEBook4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @Description: 服务端向浏览器推送数据
 * @Author: CaoXiaoLong create on 2017/7/20 18:43.
 */
@Controller
public class SseController {

    @RequestMapping(value="push",produces = "text/event-stream") //text/event-stream: 服务端对SSE的支持
    @ResponseBody
    public String push(){
        Random random = new Random();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data: test "+random.nextInt()+"\n";
    }
}
