package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MeterDto;
import com.example.metricsproducer.dto.MetricsMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class MetricsService {

  private final KafkaService kafkaService;

  private final MetricsCollector metricsCollector;

  @Scheduled(fixedRate = 5, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
  public void sendMetrics() {
    // Собираем метрики
    Map<String, Double> metricsData = metricsCollector.collectMetrics();
    MetricsMessage metrics = new MetricsMessage();
    metricsData.forEach((key, value) -> metrics.getMetrics().add(new MeterDto(key, value)));
    // Генерируем ключ
    String key = generateKey();
    kafkaService.sendMetrics(key, metrics);
  }

  private String generateKey() {
    return UUID.randomUUID().toString();
  }
}
