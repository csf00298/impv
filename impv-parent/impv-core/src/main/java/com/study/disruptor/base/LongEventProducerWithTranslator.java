package com.study.disruptor.base;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @Description:
 * Disruptor 3.0提供了lambda式的API。这样可以把一些复杂的操作放在Ring Buffer，
 * 所以在Disruptor3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件
 */
public class LongEventProducerWithTranslator {

    //一个translator可以看做一个事件初始化器，publicEvent方法会调用它
    //填充Event
    private final EventTranslatorOneArg<LongEvent,ByteBuffer> translator =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent longEvent, long l, ByteBuffer byteBuffer) {
                    longEvent.setValue(byteBuffer.get(0));
                }
            };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private void onData(ByteBuffer buffer){
        ringBuffer.publishEvent(translator,buffer);
    }
}
