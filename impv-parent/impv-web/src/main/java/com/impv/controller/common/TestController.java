package com.impv.controller.common;

import com.impv.comm.serice.RedisService;
import com.impv.db.pojo.Item;
import com.impv.db.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description: Created by Admin on 2017/3/8.
 */
@Controller
@RequestMapping("/")
public class TestController extends BaseController{


    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("testdb")
    public ModelAndView testDB(){
        List<Item> items = itemService.queryAll();
        for(Item item : items){
            System.out.println(item);
        }
        return view("redirect:views/main/about");
    }


    @RequestMapping("testredis")
    public ModelAndView testRedis(){
        redisService.set("impv","impvRedis");
        System.out.println(redisService.get("impv"));
        return view("redirect:views/main/about");
    }

}
