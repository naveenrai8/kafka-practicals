package com.nr.kafkafeatures.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaStringValueProducer {
    private final KafkaTemplate<String, String> kafkaStringValueTemplate;
    @Value("${app.kafka.topic:}")
    private String kafkaTopic;

    public void publishMessage(String s) {
        final CompletableFuture<SendResult<String, String>> futureSend = kafkaStringValueTemplate.send(MessageBuilder
                .withPayload(s)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build());
        futureSend.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("Error while sending kafka message", throwable);
            } else {
                log.info("Successfully send kafka message with offset: {}, topic: {}", result.getRecordMetadata().offset(), result.getRecordMetadata().topic());
            }
        });
    }
}
