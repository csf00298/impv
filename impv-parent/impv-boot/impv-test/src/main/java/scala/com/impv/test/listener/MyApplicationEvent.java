package scala.com.impv.test.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/3/13.
 */
public class MyApplicationEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
