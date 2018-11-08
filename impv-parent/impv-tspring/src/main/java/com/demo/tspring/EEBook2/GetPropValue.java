package com.demo.tspring.EEBook2;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * @Description 获取配置文件及系统数据
 * @Author: CaoXiaoLong create on 2017/7/19 17:56.
 */
@Configuration
@ComponentScan("com.demo.tspring.EEBook2")
@PropertySource("classpath:test/test.properties")
public class GetPropValue {

    //字符串直接注入
    @Value("String : normal")
    private String normal;

    //系统信息注入
    @Value("#{systemProperties['os.name']}")
    private String osName;

    //Java表达式 计算结果 注入
    @Value("#{T(java.lang.Math).random()*100}")
    private String randomNumber;

    //从其他类中 取对象属性
    @Value("#{otherValue.another}")
    private String fromAnother;

    //资源文件读取
    @Value("classpath:test.txt")
    private Resource testFile;

    //网络资源读取
    @Value("http://www.baidu.com")
    private Resource testUrl;

    //property配置文件直接读取
    @Value("${book.name}")
    private String bookName;

    //从environment中获取
    @Autowired
    private Environment environment;

    public void outputResource(){
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GetPropValue.class);
        GetPropValue propValue = context.getBean(GetPropValue.class);
        propValue.outputResource();
    }
}
