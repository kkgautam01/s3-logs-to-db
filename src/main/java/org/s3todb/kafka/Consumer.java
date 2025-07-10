package org.s3todb.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.s3todb.util.LogMessages;
import org.s3todb.util.Utilities;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Consumer {

    private final Logger LOGGER = Logger.getLogger(Consumer.class.getName());
    @Autowired
    Utilities util;
    private final ObjectMapper mapper = new ObjectMapper();

    KafkaConsumer<String, String> consumer;
    public Consumer() {
        Config kafkaConfig = new Config();
        this.consumer = new KafkaConsumer<>(kafkaConfig.getConsumerProperties());
    }

    public void subscribeToTopics(List<String> topics) {
        consumer.subscribe(topics);
    }

    public void read(){
        try {

            while (true) {
                // Poll for messages (100ms timeout)
                ConsumerRecords<String,  String> records = consumer.poll(Duration.ofMillis(100));

                // Check if there are any records
                if (records.isEmpty()) {
                    continue;  // Skip if no records are available
                }

                // Iterate through the records
                for (ConsumerRecord<String, String> record : records) {
                    LOGGER.info("--------Received record: Key = " + record.key() + ", Value = " + record.value() + ", Topic = " + record.topic());

                    try {
                        if (Topics.LINE_MESSAGE_TOPIC.equals(record.topic())) {
                           LOGGER.log(Level.INFO, "--------MessageLine :: " + record.value());
                            LineMessage message = deserializeLineMessage(record.value());
                            LOGGER.log(Level.INFO, "--------MessageLine LineMessage:: " + message);

                        } else {
                            LOGGER.log(Level.WARNING, LogMessages.UNKNOW_TOPIC + " : " + record);
                        }
                    } catch (Throwable e) {
                        LOGGER.log(Level.SEVERE, LogMessages.CONSUMER_MESSAGE_PARSE_ERROR + " : " + e.getMessage());
                    }
                }
                consumer.commitSync();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, LogMessages.CONSUMER_RECEIVE_ERROR + " : " + e.getMessage());
        }
    }

    public LineMessage deserializeLineMessage(String data) throws Exception {
        return mapper.readValue(data, LineMessage.class);
    }

}
