package scala.com.impv.test.extendsPackage;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:初始化类，用于扩展
 * @Author: Created by CaoXiaoLong on 2018/3/19.
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("应用中Bean 总数 -- "+applicationContext.getBeanDefinitionCount());
        for (String  str :applicationContext.getBeanDefinitionNames()){
            System.out.println(str);
        }
    }
}
