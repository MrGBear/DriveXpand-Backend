package com.example.drivebackend.dto;

import java.time.Instant;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelemetryIngestRequest(
        @NotBlank String deviceId,
        @NotNull Instant recordedAt,
        Map<String, Object> aggregation,
        Map<String, Object> metrics,
        Map<String, Object> errors
        ) {
}

