package com.example.metricsproducer.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MetricsMessage {
  private long timestamp;
  List<MeterDto> metrics;

  public MetricsMessage() {
    this.timestamp = System.currentTimeMillis();
    this.metrics = new ArrayList<>();
  }
}
