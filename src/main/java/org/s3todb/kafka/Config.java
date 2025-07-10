package org.s3todb.kafka;

import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
public class Config {
    Properties producerProperties = new Properties();
    Properties consumerProperties = new Properties();
    public Config() {
        this.producerProperties.put("bootstrap.servers", "localhost:9093"); // change if your broker is on a different host
        this.producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        this.producerProperties.put("acks", "all");

        this.consumerProperties.put("bootstrap.servers", "localhost:9093");
        this.consumerProperties.put("group.id", "message");
        this.consumerProperties.put("enable.auto.commit", "true");
        this.consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumerProperties.put("auto.offset.reset", "earliest");

    }



}
