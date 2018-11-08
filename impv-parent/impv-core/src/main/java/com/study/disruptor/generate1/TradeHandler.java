package com.study.disruptor.generate1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * @Description: 事件处理者
 * Disruptor和RingBUffer 只实现其中的一个接口即可，这里为方便合在一起了
 */
public class TradeHandler implements EventHandler<Trade>,WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long l, boolean b) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        event.setId(UUID.randomUUID().toString());
//        event.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(event.getId()+"--"+Thread.currentThread().getName());
    }
}
