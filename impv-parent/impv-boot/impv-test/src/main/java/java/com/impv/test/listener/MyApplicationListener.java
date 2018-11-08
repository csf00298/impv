package java.com.impv.test.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import scala.com.impv.test.listener.MyApplicationEvent;

/**
 * @Description:事件监听（接口方式）
 * @Author: Created by CaoXiaoLong on 2018/3/13.
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent>{
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("MyApplicationListener-------收到事件"+ event.getClass());
    }
}
