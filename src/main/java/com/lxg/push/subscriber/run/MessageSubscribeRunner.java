package com.lxg.push.subscriber.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageSubscribeRunner {

    private static final Logger logger = LoggerFactory
            .getLogger(MessageSubscribeRunner.class);

    private static AbstractApplicationContext aac;

    public static void main(String[] args) {
        aac = new ClassPathXmlApplicationContext("com/lxg/push/subscriber/subscriber.xml");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Cleaning resources, etc ...");
                aac.close();
            }
        });
    }

}
