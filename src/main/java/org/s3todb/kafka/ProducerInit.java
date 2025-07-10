package org.s3todb.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;

public class ProducerInit {

    private static KafkaProducer<String, byte[]> producer = null;

    public static void init(){
        Config kafkaConfig = new Config();
        producer = new KafkaProducer<>(kafkaConfig.getProducerProperties());
    }

    public static KafkaProducer<String, byte[]> getProducer(){
        if(producer == null){
            // Create the producer if it doesn't exist
            init();
        }
        return producer;
    }
}
