package com.nr.kafkafeatures.controller;

import com.nr.kafkafeatures.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessages(@RequestParam int count) {
        if (count <= 0 || count > 100) {
            return new ResponseEntity<>(
                    Map.of("error", "Count must be between 1 and 100"),
                    HttpStatus.BAD_REQUEST);
        }

        for (int i = 0; i < count; i++) {
            kafkaService.sendMessage("Message " + Instant.now().toString());
        }
        return ResponseEntity.ok(Map.of(
                "message", "Successfully send " + count + " messages"
        ));
    }
}
