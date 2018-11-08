package com.study.thread.eventBus.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/6/4.
 */
@Data
public class Person implements Serializable{

    private Integer Id;
    private String name ;

    public Person() {
    }

    public Person(Integer id, String name) {
        Id = id;
        this.name = name;
    }
}
