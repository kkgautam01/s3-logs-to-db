package org.s3todb.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerInit {

    private static final Logger LOGGER = Logger.getLogger(ConsumerInit.class.getName());
    static KafkaConsumer<String,  String> consumer;

    public static void init() {
        try {

            // Initialize and start the Kafka consumer
            Consumer consumer = new Consumer();  // Initialize the consumer
            consumer.subscribeToTopics(List.of(Topics.LINE_MESSAGE_TOPIC));  // Subscribe to topics

            // Start the consumer to read messages
            new Thread(consumer::read).start();  // Running in a separate thread to avoid blocking the main thread
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing consumer: " + e.getMessage(), e);
        }
    }

    public static KafkaConsumer<String,  String> getConsumer(){
        if(consumer == null){
            // Create the Consumer if it doesn't exist
            init();
        }
        return consumer;
    }

}
