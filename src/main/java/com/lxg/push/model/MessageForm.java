package com.lxg.push.model;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MessageForm {

    private String topicPrefix;
    private String topic;
    private String message;
    private Set<String> topicSuffixes;

    @JsonCreator
    public MessageForm(@JsonProperty("topicPrefix") String topicPrefix,
            @JsonProperty("topic") String topic,
            @JsonProperty("message") String message,
            @JsonProperty("topicSuffixes") Set<String> topicSuffixes) {
        this.topicPrefix = topicPrefix;
        this.topic = topic;
        this.message = message;
        this.topicSuffixes = topicSuffixes;
    }

    public String getTopicPrefix() {
        return topicPrefix;
    }
    public void setTopicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Set<String> getTopicSuffixes() {
        return topicSuffixes;
    }
    public void setTopicSuffixes(Set<String> topicSuffixes) {
        this.topicSuffixes = topicSuffixes;
    }

}
