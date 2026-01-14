package com.example.drivebackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Map;

public record TelemetryIngestRequest(
        @NotBlank String deviceId,
        @NotNull Instant recordedAt,
        Map<String, Object> aggregation,
        Map<String, Object> metrics,
        Map<String, Object> errors
        ) {
}

