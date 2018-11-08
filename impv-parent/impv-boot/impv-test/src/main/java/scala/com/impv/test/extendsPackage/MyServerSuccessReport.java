package scala.com.impv.test.extendsPackage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:在应用启动最后一步调用该类方法。通过@Order(n)注解来控制执行顺序
 * @Author: Created by CaoXiaoLong on 2018/3/19.
 */
@Order(1)
@Component
public class MyServerSuccessReport implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("应用马上启动了。。。调用了 MyServerSuccessReport");
    }
}
