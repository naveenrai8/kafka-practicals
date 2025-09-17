package com.nr.kafkafeatures.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private String name;
    private int age;
}
