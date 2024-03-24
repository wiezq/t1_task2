package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricsMessage;

import com.example.metricsconsumer.model.MeterEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaService {

  private final MetricsService metricsService;

  @KafkaListener(
      topics = "${topic.metrics-event}",
      groupId = "${spring.kafka.consumer.group-id}",
      properties = "spring.json.value.default.type=com.example.metricsconsumer.dto.MetricsMessage")
  public void consumeMetricsEvent(ConsumerRecord<String, MetricsMessage> record) {
    MetricsMessage message = record.value();
    List<MeterEntity> metrics = new ArrayList<>();
    message
        .getMetrics()
        .forEach(
            meterDto -> {
              MeterEntity meter = new MeterEntity();
              meter.setName(meterDto.getName());
              meter.setValue(meterDto.getValue());
              meter.setTimestamp(message.getTimestamp());
              metrics.add(meter);
            });
    log.info("Consumed metrics: {}", record.key());
    metricsService.saveMetrics(metrics);
  }
}
