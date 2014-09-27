package com.lxg.push.receiver.impl;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.lxg.push.model.MessageForm;
import com.lxg.push.receiver.MessageReceiver;

@Service("messageReceiver")
public class DefaultMessageReceiver implements MessageReceiver {

    private static final Logger logger = LoggerFactory
            .getLogger(MessageReceiver.class);

    @Value(value="#{prop['push.mq.exchange.cache']}")
    private String cacheExchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void enqueue(String productName, String topic, String message, String phones) {

        // TODO Check params, etc...

        Set<String> topicSuffixes = null;
        if (null != phones) {
            topicSuffixes = Sets.newHashSet(Splitter.on(",").omitEmptyStrings().split(phones));
        }

        MessageForm messageForm = new MessageForm(productName, topic, message, topicSuffixes);
        this.rabbitTemplate.convertAndSend(cacheExchangeName, messageForm,
            new MessagePostProcessor() {
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
                    if(logger.isTraceEnabled()) {
                        logger.trace("PUBLISHED Message - {}", message);
                    }
                    return message;
                }
            }
        );
    }

}
