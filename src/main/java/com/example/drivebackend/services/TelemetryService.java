package com.example.drivebackend.services;

import com.example.drivebackend.dto.TelemetryIngestRequest;
import com.example.drivebackend.dto.TelemetryResponse;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TelemetryService {

    TelemetryResponse ingestTelemetry(TelemetryIngestRequest request);

    Optional<TelemetryResponse> fetchLatestTelemetry(String deviceId);

    List<TelemetryResponse> fetchTelemetryInRange(String deviceId, Instant since, Instant end);

    // Map für alle Fahrten in gegebenen Zeitraum. Wenn für 10 min seit dem letzten TelemetryEntity kein neues gefunden wird, wird eine neue Fahrt in der map angelegt
    // Der key ist der Startzeitpunkt der Fahrt als Instant
    Map<Instant, List<TelemetryResponse>> fetchTelemetryGroupedByDrive(String deviceId, Instant since, Instant end, int timeBetweenDrivesInSeconds);
}

