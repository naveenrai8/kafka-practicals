package com.nr.kafkafeatures.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaStringValueConsumer {

    private final ConsumerFactory consumerFactory;

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${app.kafka.group-id}"
//            containerFactory = "kafkaStringValueConsumerFactory"
    )
    public void consumerHandler(String payload,
                                @Header(KafkaHeaders.OFFSET) String offset) {
        log.info("[{}]Received kafka message with offset: {}, payload: {}", this.getClass().getSimpleName(), offset, payload);
    }
}
