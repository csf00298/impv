package scala.com.impv.test;


import scala.com.impv.test.extendsPackage.MyApplicationContextInitializer;
import scala.com.impv.test.listener.MyApplicationEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/3/13.
 */
@SpringBootApplication
public class StartApp {
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(StartApp.class);

        //增加初始化 用于扩展  在app run 之前生效
        app.addInitializers(new MyApplicationContextInitializer());

        ConfigurableApplicationContext context = app.run(args);
        context.publishEvent(new MyApplicationEvent(new Object()));//添加事件
//        app.addListeners(new MyApplicationListener()); //使用spring容器实例化
        context.stop();
        context.close();
    }
}
