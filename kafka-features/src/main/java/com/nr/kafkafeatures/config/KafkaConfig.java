package com.nr.kafkafeatures.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> kafkaStringValueProducerFactory() {
        Map<String, Object> kafkaConfigProperties = new HashMap<>();
        kafkaConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaConfigProperties);
    }


    @Bean
    public ConsumerFactory<String, String> kafkaStringValueConsumerFactory() {
        Map<String, Object> kafkaConfigProperties = new HashMap<>();
        kafkaConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaConsumerFactory<>(kafkaConfigProperties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringValueTemplate() {
        return new KafkaTemplate<>(kafkaStringValueProducerFactory());
    }
}
