package com.impv.demo.controller.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: StringRedisTemplate操作Redis
 * @Author: CaoXiaoLong create on 2017/6/16 18:10.
 */
@RestController
public class RedisController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="stringRedisTemplate")
    private ValueOperations<String,String> valOpsStr;

    @RequestMapping("set")
    public String setKeyAndValue(String key,String value){
        logger.info("访问set:key={},value={}",key,value);
        valOpsStr.set(key, value);
        return "Set Ok";
    }

    @RequestMapping("get")
    public String getKey(String key){
        logger.info("访问get:key={}",key);
        return valOpsStr.get(key);
    }
}
