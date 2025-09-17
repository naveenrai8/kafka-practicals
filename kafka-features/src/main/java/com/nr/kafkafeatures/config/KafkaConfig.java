package com.nr.kafkafeatures.config;

import com.nr.kafkafeatures.model.Student;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> kafkaStringValueProducerFactory() {
        Map<String, Object> kafkaConfigProperties = commonProducerConfig();
        kafkaConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaConfigProperties);
    }

    @Bean
    public ProducerFactory<String, Student> kafkaStudentValueProducerFactory() {
        Map<String, Object> kafkaConfigProperties = commonProducerConfig();
        kafkaConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaConfigProperties);
    }


    @Bean
    public ConsumerFactory<String, String> kafkaStringValueConsumerFactory() {
        Map<String, Object> kafkaConfigProperties = commonConsumerConfig();
        kafkaConfigProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(kafkaConfigProperties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringValueTemplate() {
        return new KafkaTemplate<>(kafkaStringValueProducerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaStringValueListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaStringValueConsumerFactory());
        return factory;
    }

    private Map<String, Object> commonConsumerConfig() {
        Map<String, Object> kafkaConfigProperties = new HashMap<>();
        kafkaConfigProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaConfigProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return kafkaConfigProperties;
    }

    private Map<String, Object> commonProducerConfig() {
        Map<String, Object> kafkaConfigProperties = new HashMap<>();
        kafkaConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return kafkaConfigProperties;
    }
}
