package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MetricsMessage;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

  @Value("${topic.metrics-event}")
  private String topic;

  private final KafkaTemplate<String, MetricsMessage> kafkaTemplate;

  public void sendMetrics(String key, MetricsMessage metrics) {
    CompletableFuture.runAsync(
        () -> {
          try {
            kafkaTemplate.send(topic, key, metrics).get();
            log.info("Metrics sent to kafka");
          } catch (Exception e) {
            log.error("Error sending metrics to kafka", e);
          }
        });
  }
}
