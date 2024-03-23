package com.example.metricsproducer.controller;

import com.example.metricsproducer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsControllerImpl implements MetricsController {

  private final MetricsService metricsService;


  @PostMapping
  public ResponseEntity<Void> postMetrics() {
    metricsService.sendMetrics();
    log.info("Metrics sent to Kafka");
    return ResponseEntity.ok().build();
  }

//  @GetMapping("")
//  public ResponseEntity<?> getMetricsMessage() {
////    // Собираем метрики
////    // uptime
////    double uptime = prometheusMeterRegistry.get("process.uptime").gauge().value();
////    // start time
////    double startTime = prometheusMeterRegistry.get("process.start.time").gauge().value();
////    // heap memory usage
////    double heapMemoryUsage = prometheusMeterRegistry.get("jvm.memory.used").gauge().value();
////    // non-heap memory usage
////    double nonHeapMemoryUsage = prometheusMeterRegistry.get("jvm.memory.used").gauge().value();
////    // thread count
////    double threadCount = prometheusMeterRegistry.get("jvm.threads.live").gauge().value();
////
////    prometheusMeterRegistry.scrape();
////    // error count
//////    double errorCount =
//////        prometheusMeterRegistry.get("http.server.requests").tags("status", "500").counter().count();
////    String metrics =
////        String.format(
////            "Uptime: %f, Start Time: %f, Heap Memory Usage: %f, Non-Heap Memory Usage: %f, Thread Count: %f",
////            uptime, startTime, heapMemoryUsage, nonHeapMemoryUsage, threadCount);
//    // Собираем метрики
//    double heapMemoryUsage = meterRegistry.get("jvm.memory.used").tag("area", "heap").gauge().value();
//    double nonHeapMemoryUsage = meterRegistry.get("jvm.memory.used").tag("area", "nonheap").gauge().value();
//    double threadCount = meterRegistry.get("jvm.threads.live").gauge().value();
//    meterRegistry.counter("http.server.requests", "status", "500").count();
//    String metrics =
//        String.format(
//            "Heap Memory Usage: %f, Non-Heap Memory Usage: %f, Thread Count: %f",
//            heapMemoryUsage, nonHeapMemoryUsage, threadCount);
//    return ResponseEntity.ok().body(metrics);
//  }


//  @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
//  private void collectMetrics() {
//    prometheusMeterRegistry.forEachMeter(
//        meter -> {
//          // print all meters values
//          log.info("{}: {}", meter.getId().getName(), meter.measure());
//        });
//  }
}
