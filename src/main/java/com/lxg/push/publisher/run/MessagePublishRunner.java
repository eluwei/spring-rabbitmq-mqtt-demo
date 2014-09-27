package com.lxg.push.publisher.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessagePublishRunner {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MessagePublishRunner.class);
	
	private static AbstractApplicationContext aac; 
	
	public static void main(String[] args) {
		aac = new ClassPathXmlApplicationContext("com/lxg/push/publisher/publisher.xml");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Cleaning resources, etc ...");
                aac.close();
            }
        });
    }

}
