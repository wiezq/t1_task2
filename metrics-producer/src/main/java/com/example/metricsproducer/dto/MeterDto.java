package com.example.metricsproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MeterDto {
    private String name;
    private Double value;

}
