package com.example.metricsproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeterDto {
    private String name;
    private Double value;

}
