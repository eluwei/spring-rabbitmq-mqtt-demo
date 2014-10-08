package com.lxg.push.publisher.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.lxg.push.model.MessageForm;

@Component("messageFormSplitter")
public class MessageFormSplitter {

    public List<GenericMessage<String>> split(MessageForm messageForm) {

        List<String> topics = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(messageForm.getTopicPrefix())) {
            sb.append("/").append(messageForm.getTopicPrefix());
        }
        sb.append("/").append(messageForm.getTopic());
        String topicHeader = sb.toString();
        if (CollectionUtils.isNotEmpty(messageForm.getTopicSuffixes())) {
            for (String topicSuffix : messageForm.getTopicSuffixes()) {
                topics.add(topicHeader + "/" + topicSuffix);
            }
        } else {
            topics.add(topicHeader);
        }

        List<GenericMessage<String>> messages = new ArrayList<GenericMessage<String>>();
        for (String topic : topics) {
            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put(MqttHeaders.TOPIC, topic);
            headers.put(MqttHeaders.QOS, 1);
            //headers.put("mqtt_retained", true);
            GenericMessage<String> message = new GenericMessage<String>(messageForm.getMessage(), headers);
            messages.add(message);
        }

        return messages;
    }

}
