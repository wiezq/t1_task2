package com.example.metricsproducer.service;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MetricsCollector {

    private final MeterRegistry meterRegistry;

    public MetricsCollector(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public Map<String, Double> collectMetrics() {
        Map<String, Double> metricsData = new HashMap<>();

        metricsData.put("system.cpu.usage", getMetricValue("system.cpu.usage"));
        metricsData.put("process.uptime", getMetricValue("process.uptime"));
        metricsData.put("jvm.memory.used", convertBytesToMB(getMetricValue("jvm.memory.used")));
        metricsData.put("jvm.memory.committed", convertBytesToMB(getMetricValue("jvm.memory.committed")));
        metricsData.put("jvm.gc.memory.allocated", convertBytesToMB(getMetricValue("jvm.gc.memory.allocated")));
        metricsData.put("jvm.threads.live", getMetricValue("jvm.threads.live"));
        metricsData.put("jvm.threads.daemon", getMetricValue("jvm.threads.daemon"));
        metricsData.put("process.files.open", getMetricValue("process.files.open"));
        metricsData.put("process.cpu.usage", getMetricValue("process.cpu.usage"));

        return metricsData;
    }

    private double getMetricValue(String metricName) {
        Search search = Search.in(meterRegistry).name(s -> s.equals(metricName));
        Meter meter = search.meters().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No such meter: " + metricName));
        return meter.measure().iterator().next().getValue();
    }
    private double convertBytesToMB(double bytes) {
        return bytes / (1024 * 1024);
    }
}