package com.example.metricsproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MetricsMessage {
  private long timestamp;
  List<MeterDto> metrics;

  public MetricsMessage() {
    this.timestamp = System.currentTimeMillis();
    this.metrics = new ArrayList<>();
  }
}
