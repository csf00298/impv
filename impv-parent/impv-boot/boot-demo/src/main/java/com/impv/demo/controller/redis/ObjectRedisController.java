package com.impv.demo.controller.redis;

import com.impv.demo.bean.Person;
import com.impv.demo.dao.PersonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: RedisTemplate操作Redis
 * @Author: CaoXiaoLong create on 2017/6/19 10:35.
 */
@RestController
public class ObjectRedisController {
    protected static Logger logger= LoggerFactory.getLogger(ObjectRedisController.class);

    @Autowired
    PersonDao personDao;

    @RequestMapping("/setPerson")
    public void set(String id,String name,Integer age){
        logger.debug("访问setPerson:id={},name={},age={}",id,name,age);
        Person person=new Person(Long.parseLong(id),name,age,"");
        personDao.save(person);
    }

    @RequestMapping("/getPerson")
    public Person getPerson(String id){
        return personDao.getPerson(id);
    }
}
