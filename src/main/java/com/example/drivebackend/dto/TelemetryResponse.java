package com.example.drivebackend.dto;

import java.time.Instant;
import java.util.Map;

public record TelemetryResponse(
        String id,
        String deviceId,
        Instant start_time,
        Map<String, Object> timed_data
) {
}

