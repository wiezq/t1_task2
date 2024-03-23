package com.example.metricsproducer.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> details;
}
