package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.PageResponseDto;
import com.example.metricsconsumer.model.MeterEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface MetricsController {
  @Operation(summary = "Get a metric by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the metric",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MeterEntity.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Metric not found", content = @Content)
      })
  ResponseEntity<?> getMetric(@Parameter(description = "id of the metric to be searched") Long id);

  @Operation(summary = "Get metrics with filters")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the metrics",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PageResponseDto.class))
            }),
        @ApiResponse(responseCode = "404", description = "Metrics not found", content = @Content)
      })
  ResponseEntity<?> getMetricsWithFilter(
      @Parameter(description = "Page number") int page,
      @Parameter(description = "Name filter") String name,
      @Parameter(description = "Time from filter") LocalDateTime timeFrom,
      @Parameter(description = "Time to filter") LocalDateTime timeTo);
}
