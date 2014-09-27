package com.lxg.push.subscriber.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lxg.push.subscriber.MessageSubscriber;

@Service("messageSubscriber")
public class DefaultMessageSubscriber implements MessageSubscriber {

    private static final Logger logger = LoggerFactory
            .getLogger(DefaultMessageSubscriber.class);

    @Override
    public void process(String message) {
        if (logger.isInfoEnabled()) {
            logger.info("[{}]: [{}]", Thread.currentThread().getName(), message);
        }
    }

}
