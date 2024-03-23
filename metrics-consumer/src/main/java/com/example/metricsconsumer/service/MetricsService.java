package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.PageResponseDto;
import com.example.metricsconsumer.model.MeterEntity;
import com.example.metricsconsumer.repo.MetricRepository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {

  private final MetricRepository metricRepository;

  public void saveMetrics(List<MeterEntity> metrics) {
    metricRepository.saveAll(metrics);
  }

  public MeterEntity findMetricById(Long id) {
    return metricRepository.findById(id).orElse(null);
  }

  public Page<MeterEntity> findMetricsWithFilter(
      int page, String name, LocalDateTime timeFrom, LocalDateTime timeTo) {
    Specification<MeterEntity> spec =
        (root, query, cb) -> {
          List<Predicate> predicates = new ArrayList<>();
          if (name != null) {
            predicates.add(cb.equal(root.get("name"), name));
          }
          if (timeFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("time"), timeFrom));
          }
          if (timeTo != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("time"), timeTo));
          }
          return cb.and(predicates.toArray(new Predicate[0]));
        };

    Pageable pageable = PageRequest.of(page, 10); // 10 is the default page size
    Page<MeterEntity> resultPage = metricRepository.findAll(spec, pageable);

    return resultPage;
  }
}
