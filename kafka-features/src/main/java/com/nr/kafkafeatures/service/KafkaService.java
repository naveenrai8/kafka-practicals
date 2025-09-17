package com.nr.kafkafeatures.service;

import com.nr.kafkafeatures.producers.KafkaStringValueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaStringValueProducer kafkaStringValueProducer;
    public void sendMessage(String s) {
        kafkaStringValueProducer.publishMessage(s);
    }
}
