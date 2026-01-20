package com.example.drivebackend.dto;

import tools.jackson.databind.JsonNode;
import java.time.Instant;
import java.util.UUID;

public record TelemetryResponse(
        UUID id,
        String deviceId,
        Instant recordedAt,
        Long startTime,
        Long endTime,
        JsonNode aggregatedData,
        JsonNode metrics
) {
}

