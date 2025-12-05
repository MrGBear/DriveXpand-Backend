package com.example.drivebackend.services;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.dto.TelemetryResponse;

import java.util.Optional;

public interface TelemetryService {

    TelemetryResponse ingestTelemetry(TelemetryIngestRequest request);

    Optional<TelemetryResponse> fetchLatestTelemetry(String deviceId);
}

