package com.example.metricsconsumer.repo;

import com.example.metricsconsumer.model.MeterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<MeterEntity, Long>, JpaSpecificationExecutor<MeterEntity> {}
