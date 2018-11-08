package com.study.Java8.prepare;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2017/11/21.
 */
public class Person {
    private Integer id;
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(Integer id,  String name,Integer age) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}