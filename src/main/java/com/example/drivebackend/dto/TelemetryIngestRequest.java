package com.example.drivebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record TelemetryIngestRequest(
        @NotBlank String deviceId,
        @NotNull Instant recordedAt,
        @JsonProperty("start_time") Long startTime,
        @JsonProperty("end_time") Long endTime,
        @JsonProperty("aggregated_data") JsonNode aggregatedData,
        JsonNode metrics
) {
}

