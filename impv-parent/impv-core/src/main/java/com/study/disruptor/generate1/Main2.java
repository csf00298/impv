package com.study.disruptor.generate1;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 使用WorkerPool接口
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE=1024;
        int THREAD_NUMBERS=4;

        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        },BUFFER_SIZE);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBERS);

        WorkHandler<Trade> handler = new TradeHandler();
        WorkerPool<Trade> workerPool = new WorkerPool<Trade>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),handler);

        workerPool.start(executorService);

        //下面这个生产8个数据
        for (int i = 0; i < 8; i++) {
            long seq = ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random()*9998);
            ringBuffer.publish(seq);
        }

        Thread.sleep(1000);
        workerPool.halt(); //通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executorService.shutdown();
    }
}
