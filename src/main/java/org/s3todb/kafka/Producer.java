package org.s3todb.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.adapter.ProviderAdapter;
import org.s3todb.util.LogMessages;
import org.s3todb.util.Utilities;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Producer {
    @Autowired
    static Utilities util;
    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
    static ObjectMapper mapper = new ObjectMapper();

    public Producer() {

    }

    public static void sendData(String fileData, ProviderAdapter adapter,
                                Provider provider,
                                String fileType,
                                Map<String, Integer> languageIdMap,
                                Map<String, Integer> ratingCategoryIdMap,
                                int retry,Map<String, String> providerMap) {

        try {
            KafkaProducer<String, byte[]> producer = ProducerInit.getProducer();
            LineMessage lineMessage = new LineMessage(providerMap, languageIdMap,
                    ratingCategoryIdMap, fileData, fileType, retry);

            // Serialize LineMessage
            byte[] message = serializeLineMessage(lineMessage);

            // Send message to Kafka topic
            ProducerRecord<String, byte[]> record = new ProducerRecord<>(Topics.LINE_MESSAGE_TOPIC, "message", message);

            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    LOGGER.log(Level.SEVERE, LogMessages.MESSAGE_COULD_NOT_SEND + " :  " + exception.getMessage());
                }
            });

        }catch(Exception e){
            LOGGER.log(Level.SEVERE, LogMessages.PRODUCER_SENT_ERROR + " : " + e.getMessage());

        }
    }

    public static byte[] serializeLineMessage(LineMessage message) throws Exception {

        return mapper.writeValueAsBytes(message);
    }
}
