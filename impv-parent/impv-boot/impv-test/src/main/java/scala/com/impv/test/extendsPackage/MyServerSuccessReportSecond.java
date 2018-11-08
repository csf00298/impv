package scala.com.impv.test.extendsPackage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:在应用启动最后一步调用该类方法
 * @Author: Created by CaoXiaoLong on 2018/3/19.
 */
@Order(2)
@Component
public class MyServerSuccessReportSecond implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("应用马上启动了。。。调用了 MyServerSuccessReportSecond ");
    }
}
