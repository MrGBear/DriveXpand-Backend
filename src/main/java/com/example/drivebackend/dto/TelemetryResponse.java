package com.example.drivebackend.dto;

import java.time.Instant;
import java.util.Map;

public record TelemetryResponse(
        String id,
        String deviceId,
        Instant recordedAt,
        Double latitude,
        Double longitude,
        Map<String, Object> metrics
) {
}

