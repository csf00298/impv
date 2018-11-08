package com.demo.tspring.EEBook2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 本类中读取数据 供其他使用
 * @Author: CaoXiaoLong create on 2017/7/19 18:07.
 */
@Component
public class OtherValue {

    @Value("anotherValue")
    public String another;
}
