package com.study.thread.eventBus;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yanshi on 2017/4/20.
 */
@Slf4j
public class EventBusUtil {
    private Map<Integer, EventBus> motorcade = Maps.newConcurrentMap();
    private List eventHandlers;
    private int busSize;

    public EventBusUtil(int busSize, List eventHandlers) {
        this.busSize = busSize;
        this.eventHandlers = eventHandlers;
        for (int i = 0; i < busSize; i++) {
            System.out.println(("线程池当前总数：" + i));//TODO
            motorcade.put(i, createBus("bus-no" + i));
        }
    }

    private EventBus createBus(String name) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20000));
        EventBus eventBus = new AsyncEventBus(threadPoolExecutor, new ChannelSubscriberExceptionHandler(name));
        eventHandlers.forEach(eventBus::register);
        return eventBus;
    }

    private static int iterNum = 0;

    public void send(Object event, String key) {
        EventBus eventBus = motorcade.get(iterNum++ % motorcade.size());
        eventBus.post(event);
    }


    private int findIndex(String key) {
        HashCode hashCode = Hashing.md5().newHasher().putString(key, Charsets.UTF_8).hash();
        int hash = hashCode.asInt();
        return Math.abs(hash) % (Math.max(busSize - 1, 1));
    }

    private static class ChannelSubscriberExceptionHandler implements SubscriberExceptionHandler {
        private final Logger logger;

        public ChannelSubscriberExceptionHandler(String identifier) {
            logger = LoggerFactory.getLogger(
                    EventBus.class.getName() + "." + checkNotNull(identifier));
        }

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            logger.error("Could not dispatch event: {} to {} error info {}", context.getSubscriber(),
                    context.getSubscriberMethod(), ExceptionUtils.getStackTrace(exception));
        }
    }

}
