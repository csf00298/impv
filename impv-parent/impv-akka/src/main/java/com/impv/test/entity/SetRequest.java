package com.impv.test.entity;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/5/8.
 */
public class SetRequest {
    private final String key;
    private final Object value;

    public SetRequest(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
