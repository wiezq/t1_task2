package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.PageResponseDto;
import com.example.metricsconsumer.model.MeterEntity;
import com.example.metricsconsumer.service.MetricsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/metrics")
@AllArgsConstructor
public class MetricsControllerImpl implements MetricsController{

    private final MetricsService metricsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMetric(@PathVariable Long id) {
        MeterEntity metric = metricsService.findMetricById(id);
        if (metric == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(metric);
    }

    @GetMapping
    public ResponseEntity<?> getMetricsWithFilter(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "timeFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
            @RequestParam(value = "timeTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo
    ) {
        Page<MeterEntity> metrics = metricsService.findMetricsWithFilter(page, name, timeFrom, timeTo);
        if (metrics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PageResponseDto<MeterEntity> metricsResponse = new PageResponseDto<>();
        metricsResponse.setContent(metrics.getContent());
        metricsResponse.setPageNumber(metrics.getNumber());
        metricsResponse.setPageSize(metrics.getSize());
        metricsResponse.setTotalElements(metrics.getTotalElements());
        metricsResponse.setTotalPages(metrics.getTotalPages());
        return ResponseEntity.ok(metricsResponse);
    }
}
