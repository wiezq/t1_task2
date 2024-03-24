package com.example.metricsconsumer.dto;

import java.util.List;
import lombok.Data;

@Data
public class MetricsMessage {
  private long timestamp;
  List<MeterDto> metrics;
}
