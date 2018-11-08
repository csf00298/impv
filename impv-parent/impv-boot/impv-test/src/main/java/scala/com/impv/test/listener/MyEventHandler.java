package scala.com.impv.test.listener;

import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description:事件监听（注解方式）
 * @Author: Created by CaoXiaoLong on 2018/3/16.
 */
@Component
public class MyEventHandler {

    @EventListener
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("MyApplicationEvent-------收到事件"+ event.getClass());
    }

    @EventListener
    public void stopEvent(ContextStoppedEvent stoppedEvent){
        System.out.println("______application stop 应用程序停止");
    }
}
