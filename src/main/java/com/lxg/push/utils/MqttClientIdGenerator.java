package com.lxg.push.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class MqttClientIdGenerator {

    private static final int MAX_LENGTH = 22;

    public String generate(String prefix) {
        if (prefix.length() > MAX_LENGTH) {
            prefix = prefix.substring(0, MAX_LENGTH);
        }
        return prefix + "-" + RandomStringUtils.randomAlphanumeric(MAX_LENGTH - prefix.length());
    }

}
