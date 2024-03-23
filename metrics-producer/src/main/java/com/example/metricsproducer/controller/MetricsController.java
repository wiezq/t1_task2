package com.example.metricsproducer.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

@Tag(name = "Metrics", description = "The Metrics API")
public interface MetricsController {

  @Operation(summary = "Post metrics", description = "Post metrics")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Metrics posted"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
      })
  public ResponseEntity<Void> postMetrics();
}
