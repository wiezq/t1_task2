package com.example.metricsconsumer.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MetricsMessage {
  private long timestamp;
  List<MeterDto> metrics;
}
